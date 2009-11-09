#include "ruby.h"
#include "ext_help.h"
#include <assert.h>
#include <string.h>
#include "gherkin_lexer.h"

#ifndef RSTRING_PTR
#define RSTRING_PTR(s) (RSTRING(s)->ptr)
#endif
#ifndef RSTRING_LEN
#define RSTRING_LEN(s) (RSTRING(s)->len)
#endif

static VALUE mGherkin;
static VALUE mLexer;
static VALUE cCLexer; 
static VALUE rb_eGherkinLexerError;

void store_comment_content(void *listener, const char *at, size_t length, int line) 
{ 
  VALUE val = Qnil;

  val = rb_str_new(at, length);
  rb_funcall((VALUE)listener, rb_intern("comment"), 2, val, INT2FIX(line));
}

void store_tag_content(void *listener, const char *at, size_t length, int line)
{
  VALUE val = Qnil;

  val = rb_str_new(at, length);
  rb_funcall((VALUE)listener, rb_intern("tag"), 2, val, INT2FIX(line)); 
} 

void raise_lexer_error(void *listener, const char *at, int line)
{ 
  rb_raise(rb_eGherkinLexerError, "Parsing error on line %d: '%s'.", line, at); //, INT2FIX(line));
}

void store_feature_content(void *listener, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = rb_str_new(keyword_at, keyword_length);
  con = rb_str_new(at, length);
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("chop!"), 0);
  rb_funcall((VALUE)listener, rb_intern("feature"), 3, kw, con, INT2FIX(current_line)); 
}

void store_scenario_content(void *listener, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = rb_str_new(keyword_at, keyword_length);
  con = rb_str_new(at, length);
  // Need multiline strip for con here
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("chop!"), 0);
  rb_funcall((VALUE)listener, rb_intern("scenario"), 3, kw, con, INT2FIX(current_line)); 
}

void store_scenario_outline_content(void *listener, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = rb_str_new(keyword_at, keyword_length);
  con = rb_str_new(at, length);
  // Need multiline strip for con here
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("chop!"), 0);
  rb_funcall((VALUE)listener, rb_intern("scenario_outline"), 3, kw, con, INT2FIX(current_line)); 
}

void store_background_content(void *listener, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = rb_str_new(keyword_at, keyword_length);
  con = rb_str_new(at, length);
  // Need multiline strip for con here
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("chop!"), 0);
  rb_funcall((VALUE)listener, rb_intern("background"), 3, kw, con, INT2FIX(current_line)); 
}

void store_examples_content(void *listener, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = rb_str_new(keyword_at, keyword_length);
  con = rb_str_new(at, length);
  // Need multiline strip for con here
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("chop!"), 0);
  rb_funcall((VALUE)listener, rb_intern("examples"), 3, kw, con, INT2FIX(current_line)); 
}

void store_step_content(void *listener, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = rb_str_new(keyword_at, keyword_length);
  con = rb_str_new(at, length);
  // Need multiline strip for con here
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(kw, rb_intern("strip!"), 0);
  rb_funcall((VALUE)listener, rb_intern("step"), 3, kw, con, INT2FIX(current_line)); 
}

void store_pystring_content(void *listener, int start_col, const char *at, size_t length, int current_line)
{
  VALUE con = Qnil;
  con = rb_str_new(at, length);
  rb_funcall((VALUE)listener, rb_intern("py_string"), 3, INT2FIX(start_col), con, INT2FIX(current_line));
}

void store_table(void *listener, int current_line)
{
  rb_funcall((VALUE)listener, rb_intern("table"), 2, rb_ary_new(), INT2FIX(current_line));
}

void CLexer_free(void *data) 
{
  if(data) {
    free(data);
  }
}

VALUE CLexer_alloc(VALUE klass)
{
  VALUE obj;
  lexer *psr = ALLOC_N(lexer, 1);
  psr->store_comment_content = store_comment_content;
  psr->store_tag_content = store_tag_content;
  psr->store_feature_content = store_feature_content;
  psr->store_scenario_content = store_scenario_content;
  psr->store_scenario_outline_content = store_scenario_outline_content;
  psr->store_background_content = store_background_content;
  psr->store_examples_content = store_examples_content;
  psr->store_step_content = store_step_content;
  psr->store_pystring_content = store_pystring_content;
  psr->raise_lexer_error = raise_lexer_error;
  psr->store_table = store_table;
  lexer_init(psr);

  obj = Data_Wrap_Struct(klass, NULL, CLexer_free, psr);

  return obj;
}

VALUE CLexer_init(VALUE self, VALUE listener)
{
  rb_iv_set(self, "@listener", listener);
  lexer *psr = NULL;
  DATA_GET(self, lexer, psr);
  lexer_init(psr);
  psr->listener = ROBJECT(listener);

  return self;
}

VALUE CLexer_reset(VALUE self)
{
  lexer *psr = NULL;
  DATA_GET(self, lexer, psr);
  lexer_init(psr);
 
  return Qnil;
}

VALUE CLexer_scan(VALUE self, VALUE data)
{
  lexer *psr = NULL;
  char *dptr = NULL;
  long dlen = 0;
 
  DATA_GET(self, lexer, psr);

  rb_str_append(data, rb_str_new2("\n%_FEATURE_END_%"));
  dptr = RSTRING_PTR(data);
  dlen = RSTRING_LEN(data);

  // from is always 0.  if dlen = 0, 
  if(dlen == 0) { 
    rb_raise(rb_eGherkinLexerError, 0, "No content to parse.");
  } else {
    lexer_scan(psr, dptr, dlen);

    if(lexer_has_error(psr)) {
      rb_raise(rb_eGherkinLexerError, 0, "Invalid format, parsing fails.");
    } else {
      return 1;
    }
  }
}

VALUE CLexer_has_error(VALUE self)
{
  lexer *psr = NULL;
  DATA_GET(self, lexer, psr);
  
  return lexer_has_error(psr) ? Qtrue : Qfalse;
}

void Init_gherkin_lexer()
{
  mGherkin = rb_define_module("Gherkin");
  mLexer = rb_define_module_under(mGherkin, "Lexer");
  cCLexer = rb_define_class_under(mLexer, "CLexer", rb_cObject);
  rb_define_alloc_func(cCLexer, CLexer_alloc);
  rb_define_method(cCLexer, "initialize", CLexer_init,1);
  rb_define_method(cCLexer, "reset", CLexer_reset,0);
  rb_define_method(cCLexer, "scan", CLexer_scan,1);
  rb_define_method(cCLexer, "error?", CLexer_has_error,0);
  
  rb_eGherkinLexerError = rb_const_get(mLexer, rb_intern("ParsingError"));
}
