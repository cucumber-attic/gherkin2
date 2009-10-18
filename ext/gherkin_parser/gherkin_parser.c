#include "ruby.h"
#include "ext_help.h"
#include <assert.h>
#include <string.h>
#include "gherkin_parser.h"

#ifndef RSTRING_PTR
#define RSTRING_PTR(s) (RSTRING(s)->ptr)
#endif
#ifndef RSTRING_LEN
#define RSTRING_LEN(s) (RSTRING(s)->len)
#endif

static VALUE mGherkin;
static VALUE mParser;
static VALUE cCParser; 
static VALUE eCParserError;

void store_comment_content(void *listener, void *data, const char *at, size_t length, int line) 
{ 
  VALUE val = Qnil;

  val = rb_str_new(at, length);
  rb_funcall(listener, rb_intern("comment"), 2, val, INT2FIX(line));
}

void store_tag_content(void *listener, void *data, const char *at, size_t length, int line)
{
  VALUE val = Qnil;

  val = rb_str_new(at, length);
  rb_funcall(listener, rb_intern("tag"), 2, val, INT2FIX(line)); 
} 

void store_feature_content(void *listener, void *data, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = rb_str_new(keyword_at, keyword_length);
  con = rb_str_new(at, length);
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("chop!"), 0);
  // Fake keyword for Feature (English only right now)
  rb_funcall(listener, rb_intern("feature"), 3, kw, con, INT2FIX(current_line)); 
}

void CParser_free(void *data) 
{
  if(data) {
    free(data);
  }
}

VALUE CParser_alloc(VALUE klass)
{
  VALUE obj;
  parser *psr = ALLOC_N(parser, 1);
  psr->store_comment_content = store_comment_content;
  psr->store_tag_content = store_tag_content;
  psr->store_feature_content = store_feature_content;
  parser_init(psr);

  obj = Data_Wrap_Struct(klass, NULL, CParser_free, psr);

  return obj;
}

VALUE CParser_init(VALUE self, VALUE listener)
{
  rb_iv_set(self, "@listener", listener);
  parser *psr = NULL;
  DATA_GET(self, parser, psr);
  parser_init(psr);
  psr->listener = ROBJECT(listener);

  return self;
}

VALUE CParser_reset(VALUE self)
{
  parser *psr = NULL;
  DATA_GET(self, parser, psr);
  parser_init(psr);
 
  return Qnil;
}

VALUE CParser_scan(VALUE self, VALUE data)
{
  parser *psr = NULL;
  int from = 0;
  char *dptr = NULL;
  long dlen = 0;
 
  DATA_GET(self, parser, psr);

  dptr = RSTRING_PTR(data);
  dlen = RSTRING_LEN(data);

  // from is always 0.  if dlen = 0, 
  if(dlen == 0) { 
    rb_raise(eCParserError, "No content to parse.");
  } else {
    parser_scan(psr, dptr, dlen, from);

    if(parser_has_error(psr)) {
      rb_raise(eCParserError, "Invalid format, parsing fails.");
    } else {
      return INT2FIX(parser_nread(psr));
    }
  }
}

VALUE CParser_has_error(VALUE self)
{
  parser *psr = NULL;
  DATA_GET(self, parser, psr);
  
  return parser_has_error(psr) ? Qtrue : Qfalse;
}

VALUE CParser_nread(VALUE self)
{
  parser *psr = NULL;
  DATA_GET(self, parser, psr);
  
  return INT2FIX(psr->nread);
}

void Init_gherkin_parser()
{
  mGherkin = rb_define_module("Gherkin");
  mParser = rb_define_module_under(mGherkin, "Parser");
  
  eCParserError = rb_define_class_under(mParser, "GherkinCParserError", rb_eIOError);

  cCParser = rb_define_class_under(mParser, "CParser", rb_cObject);
  rb_define_alloc_func(cCParser, CParser_alloc);
  rb_define_method(cCParser, "initialize", CParser_init,1);
  rb_define_method(cCParser, "reset", CParser_reset,0);
  rb_define_method(cCParser, "scan", CParser_scan,1);
  rb_define_method(cCParser, "error?", CParser_has_error,0);
  rb_define_method(cCParser, "nread", CParser_nread,0);
}
