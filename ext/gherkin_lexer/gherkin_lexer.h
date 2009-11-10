#ifndef gherkin_lexer_h
#define gherkin_lexer_h

#include <sys/types.h>

#if defined(_WIN32)
#include <stddef.h>
#endif


typedef void (*listener_cb)(void *listener, const char *at, size_t length, int line);
typedef void (*listener_error_cb)(void *listener, const char *at, int line);
typedef void (*listener_long_cb)(void *listener, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int line);
typedef void (*listener_pystring_cb)(void *listener, int start_col, const char *at, size_t length, int line);
typedef void (*listener_table_cb)(void *listener, void *table, int line);
typedef void (*ruby_table_cb)(void *lexer);
typedef void (*ruby_table_cell_cb)(void *lexer, const char *at, size_t length);

typedef struct lexer {
  int cs;
  int content_len;
  int line_number;
  int current_line;
  int start_col;
  size_t mark;
  size_t keyword_start;
  size_t keyword_end;
  size_t next_keyword_start;
  size_t content_start;
  size_t content_end;
  size_t field_len;
  size_t query_start;
  size_t last_newline;
  size_t final_newline;

  void *listener;
  void *data;
  void *table;
  void *row;

  listener_cb store_comment_content;
  listener_cb store_tag_content;
  listener_error_cb raise_lexer_error;
  listener_long_cb store_feature_content;
  listener_long_cb store_scenario_content;
  listener_long_cb store_scenario_outline_content;
  listener_long_cb store_background_content;
  listener_long_cb store_examples_content;
  listener_long_cb store_step_content;
  listener_pystring_cb store_pystring_content;
  listener_table_cb store_table;
  ruby_table_cb initialize_table;
  ruby_table_cb new_row;
  ruby_table_cb end_row;
  ruby_table_cell_cb add_cell_to_current_row;
} lexer;

int lexer_init(lexer *lxr);
size_t lexer_scan(lexer *lxr, const char *data, size_t len);
int lexer_has_error(lexer *lxr);

#endif
