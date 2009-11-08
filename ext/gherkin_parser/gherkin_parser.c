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
static VALUE rb_eGherkinParserError;

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

void raise_parser_error(void *listener, const char *at, int line)
{ 
  rb_raise(rb_eGherkinParserError, "Parsing error on line %d: '%s'.", line, at); //, INT2FIX(line));
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

void initialize_table(parser *psr)
{
  VALUE table, row;
  table = rb_ary_new();
  row = rb_ary_new();
  psr->table = RARRAY(table);
  psr->row = RARRAY(row);
}

void add_cell_to_current_row(parser *psr, const char *at, size_t length)
{
  VALUE con = Qnil;
  con = rb_str_new(at, length);
  rb_funcall(con, rb_intern("strip!"), 0);

  rb_ary_push(psr->row, con);
}

void new_row(parser *psr)
{
  VALUE row;
  row = rb_ary_new();
  psr->row = row;
}

void end_row(parser *psr)
{
  rb_ary_push(psr->table, psr->row);
}

void store_table(void *listener, void *table, int current_line)
{
  rb_funcall((VALUE)listener, rb_intern("table"), 2, table, INT2FIX(current_line));
  VALUE new_table;
  new_table = rb_ary_new;
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
  psr->store_scenario_content = store_scenario_content;
  psr->store_scenario_outline_content = store_scenario_outline_content;
  psr->store_background_content = store_background_content;
  psr->store_examples_content = store_examples_content;
  psr->store_step_content = store_step_content;
  psr->store_pystring_content = store_pystring_content;
  psr->raise_parser_error = raise_parser_error;
  psr->store_table = store_table;
  psr->initialize_table = initialize_table;
  psr->add_cell_to_current_row = add_cell_to_current_row;
  psr->new_row = new_row;
  psr->end_row = end_row;
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
  VALUE table, row;
  table = rb_ary_new();
  row = rb_ary_new();
  psr->table = RARRAY(table);
  psr->row = RARRAY(row);

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
  char *dptr = NULL;
  long dlen = 0;
 
  DATA_GET(self, parser, psr);

  rb_str_append(data, rb_str_new2("\n%_FEATURE_END_%"));
  dptr = RSTRING_PTR(data);
  dlen = RSTRING_LEN(data);

  // from is always 0.  if dlen = 0, 
  if(dlen == 0) { 
    rb_raise(rb_eGherkinParserError, 0, "No content to parse.");
  } else {
    parser_scan(psr, dptr, dlen);

    if(parser_has_error(psr)) {
      rb_raise(rb_eGherkinParserError, 0, "Invalid format, parsing fails.");
    } else {
      return 1;
    }
  }
}

VALUE CParser_has_error(VALUE self)
{
  parser *psr = NULL;
  DATA_GET(self, parser, psr);
  
  return parser_has_error(psr) ? Qtrue : Qfalse;
}

void Init_gherkin_parser()
{
  mGherkin = rb_define_module("Gherkin");
  mParser = rb_define_module_under(mGherkin, "Parser");
  cCParser = rb_define_class_under(mParser, "CParser", rb_cObject);
  rb_define_alloc_func(cCParser, CParser_alloc);
  rb_define_method(cCParser, "initialize", CParser_init,1);
  rb_define_method(cCParser, "reset", CParser_reset,0);
  rb_define_method(cCParser, "scan", CParser_scan,1);
  rb_define_method(cCParser, "error?", CParser_has_error,0);
  
  rb_eGherkinParserError = rb_const_get(mParser, rb_intern("ParsingError"));
}
