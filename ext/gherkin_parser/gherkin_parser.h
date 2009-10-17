#ifndef gherkin_parser_h
#define gherkin_parser_h

#include <sys/types.h>

#if defined(_WIN32)
#include <stddef.h>
#endif

typedef struct parser {
  int cs;
  size_t body_start;
  int content_len;
  size_t nread;
  size_t mark;
  size_t field_start;
  size_t field_len;
  size_t query_start;
  
//  void *data; (I think this was storing the params hash.  Confusing because ragel uses data a lot

} parser;

int parser_init(parser *psr);
size_t parser_scan(parser *psr, const char *data, size_t len, size_t off);
int parser_has_error(parser *psr);

#define parser_nread(psr) (psr)->nread

#endif
