#ifndef gherkin_parser_h
#define gherkin_parser_h

#include <sys/types.h>

#if defined(_WIN32)
#include <stddef.h>
#endif


typedef void (*listener_cb)(void *listener, void *data, const char *at, size_t length, int line);
typedef void (*long_listener_cb)(void *listener, void *data, const char *keyword_at, size_t keyword_length, const char *at, size_t length, int line);

typedef struct parser {
  int cs;
  int content_len;
  int line_number;
  int current_line;
  size_t nread;
  size_t mark;
  size_t keyword_start;
  size_t keyword_end;
  size_t next_keyword_start;
  size_t content_start;
  size_t content_end;
  size_t field_len;
  size_t query_start;

  void *listener;
  void *data;

  listener_cb store_comment_content;
  listener_cb store_tag_content;
  long_listener_cb store_feature_content;
  long_listener_cb store_scenario_content;
  long_listener_cb store_step_content;
} parser;

int parser_init(parser *psr);
size_t parser_scan(parser *psr, const char *data, size_t len, size_t off);
int parser_has_error(parser *psr);

#define parser_nread(psr) (psr)->nread

#endif
