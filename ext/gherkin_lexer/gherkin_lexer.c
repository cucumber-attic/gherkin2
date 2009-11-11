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

static VALUE strip_i(VALUE str, VALUE ary)
{
  rb_funcall(str, rb_intern("strip!"), 0);
  rb_ary_push(ary, str);
  
  return Qnil;
}

static VALUE multiline_strip(VALUE text)
{
  VALUE map = rb_ary_new();
  VALUE split = rb_str_split(text, "\n");
  
  rb_iterate(rb_each, split, strip_i, map);
  
  return rb_ary_join(split, rb_str_new2("\n"));
}

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
  rb_raise(rb_eGherkinLexerError, "Lexing error on line %d: '%s'.", line, at);
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
  con = multiline_strip(con);
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
  con = multiline_strip(con);
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
  con = multiline_strip(con);
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
  con = multiline_strip(con);
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
  con = multiline_strip(con);
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

void initialize_table(lexer *lxr)
{
//    rb_ary_clear((VALUE)lxr->table);
//    rb_ary_clear((VALUE)lxr->row);
  VALUE table, row;
  table = rb_ary_new();
  row = rb_ary_new();
  lxr->table = RARRAY(table);
  lxr->row = RARRAY(row);
}

void add_cell_to_current_row(lexer *lxr, const char *at, size_t length)
{
  VALUE con = Qnil;
  con = rb_str_new(at, length);
  rb_funcall(con, rb_intern("strip!"), 0);

  rb_ary_push((VALUE)lxr->row, con);
}

void new_row(lexer *lxr)
{
  VALUE row = rb_ary_new();
  lxr->row = RARRAY(row);
//    rb_ary_clear((VALUE)lxr->row);
}

void end_row(lexer *lxr)
{
//  rb_ary_push((VALUE)lxr->table, rb_ary_dup((VALUE)lxr->row));
  rb_ary_push((VALUE)lxr->table, (VALUE)lxr->row);
}

void store_table(void *listener, void *table, int current_line)
{
  rb_funcall((VALUE)listener, rb_intern("table"), 2, table, INT2FIX(current_line));
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
  lexer *lxr = ALLOC_N(lexer, 1);
  lxr->store_comment_content = store_comment_content;
  lxr->store_tag_content = store_tag_content;
  lxr->store_feature_content = store_feature_content;
  lxr->store_scenario_content = store_scenario_content;
  lxr->store_scenario_outline_content = store_scenario_outline_content;
  lxr->store_background_content = store_background_content;
  lxr->store_examples_content = store_examples_content;
  lxr->store_step_content = store_step_content;
  lxr->store_pystring_content = store_pystring_content;
  lxr->raise_lexer_error = raise_lexer_error;
  lxr->store_table = store_table;
  lxr->initialize_table = (void *)initialize_table;
  lxr->add_cell_to_current_row = (void *)add_cell_to_current_row;
  lxr->new_row = (void *)new_row;
  lxr->end_row = (void *)end_row;
  lexer_init(lxr);

  obj = Data_Wrap_Struct(klass, NULL, CLexer_free, lxr);

  return obj;
}

VALUE CLexer_init(VALUE self, VALUE listener)
{
  rb_iv_set(self, "@listener", listener);
  lexer *lxr = NULL;
  DATA_GET(self, lexer, lxr);
  lexer_init(lxr);
  lxr->listener = ROBJECT(listener);
  VALUE table, row;
  table = rb_ary_new();
  row = rb_ary_new();
  lxr->table = RARRAY(table);
  lxr->row = RARRAY(row);

  return self;
}

VALUE CLexer_reset(VALUE self)
{
  lexer *lxr = NULL;
  DATA_GET(self, lexer, lxr);
  lexer_init(lxr);
 
  return Qnil;
}

VALUE CLexer_scan(VALUE self, VALUE data)
{
  lexer *lxr = NULL;
  char *dptr = NULL;
  long dlen = 0;
 
  DATA_GET(self, lexer, lxr);

  rb_str_append(data, rb_str_new2("\n%_FEATURE_END_%"));
  dptr = RSTRING_PTR(data);
  dlen = RSTRING_LEN(data);

  if(dlen == 0) { 
    rb_raise(rb_eGherkinLexerError, 0, "No content to parse.");
  } else {
    lexer_scan(lxr, dptr, dlen);

    if(lexer_has_error(lxr)) {
      rb_raise(rb_eGherkinLexerError, 0, "Invalid format, parsing fails.");
    } else {
      return 1;
    }
  }
}

VALUE CLexer_has_error(VALUE self)
{
  lexer *lxr = NULL;
  DATA_GET(self, lexer, lxr);
  
  return lexer_has_error(lxr) ? Qtrue : Qfalse;
}

void Init_gherkin_lexer()
{
  mGherkin = rb_define_module("Gherkin");
  mLexer = rb_define_module_under(mGherkin, "Lexer");
  cCLexer = rb_define_class_under(mLexer, "CLexer", rb_cObject);
  rb_define_alloc_func(cCLexer, CLexer_alloc);
  rb_define_method(cCLexer, "initialize", CLexer_init, 1);
  rb_define_method(cCLexer, "reset", CLexer_reset, 0);
  rb_define_method(cCLexer, "scan", CLexer_scan, 1);
  rb_define_method(cCLexer, "error?", CLexer_has_error, 0);
  
  rb_eGherkinLexerError = rb_const_get(mLexer, rb_intern("LexingError"));
}
