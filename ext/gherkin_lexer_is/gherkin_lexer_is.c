
#line 1 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
#include <assert.h>
#include <ruby.h>

#if defined(_WIN32)
#include <stddef.h>
#endif

#ifdef HAVE_RUBY_RE_H
#include <ruby/re.h>
#else
#include <re.h>
#endif

#ifdef HAVE_RUBY_ENCODING_H
#include <ruby/encoding.h>
#define ENCODED_STR_NEW(ptr, len) \
    rb_enc_str_new(ptr, len, rb_utf8_encoding())
#else
#define ENCODED_STR_NEW(ptr, len) \
    rb_str_new(ptr, len)
#endif

#ifndef RSTRING_PTR
#define RSTRING_PTR(s) (RSTRING(s)->ptr)
#endif

#ifndef RSTRING_LEN
#define RSTRING_LEN(s) (RSTRING(s)->len)
#endif

#define DATA_GET(FROM, TYPE, NAME) \
  Data_Get_Struct(FROM, TYPE, NAME); \
  if (NAME == NULL) { \
    rb_raise(rb_eArgError, "NULL found for " # NAME " when it shouldn't be."); \
  }
 
typedef struct lexer_state {
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
  size_t query_start;
  size_t last_newline;
  size_t final_newline;
} lexer_state;

static VALUE mGherkin;
static VALUE mGherkinLexer;
static VALUE mCLexer;
static VALUE cI18nLexer;
static VALUE rb_eGherkinLexingError;

#define LEN(AT, P) (P - data - lexer->AT)
#define MARK(M, P) (lexer->M = (P) - data)
#define PTR_TO(P) (data + lexer->P)

#define STORE_KW_END_CON(EVENT) \
  store_multiline_kw_con(listener, # EVENT, \
    PTR_TO(keyword_start), LEN(keyword_start, PTR_TO(keyword_end - 1)), \
    PTR_TO(content_start), LEN(content_start, PTR_TO(content_end)), \
    lexer->current_line, lexer->start_col); \
    if (lexer->content_end != 0) { \
      p = PTR_TO(content_end - 1); \
    } \
    lexer->content_end = 0

#define STORE_ATTR(ATTR) \
    store_attr(listener, # ATTR, \
      PTR_TO(content_start), LEN(content_start, p), \
      lexer->line_number)


#line 242 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"


/** Data **/

#line 87 "ext/gherkin_lexer_is/gherkin_lexer_is.c"
static const char _lexer_actions[] = {
	0, 1, 0, 1, 1, 1, 2, 1, 
	3, 1, 4, 1, 5, 1, 6, 1, 
	7, 1, 8, 1, 9, 1, 10, 1, 
	11, 1, 14, 1, 15, 1, 16, 1, 
	17, 1, 18, 1, 19, 1, 20, 1, 
	21, 2, 1, 16, 2, 11, 0, 2, 
	12, 13, 2, 15, 0, 2, 15, 2, 
	2, 15, 14, 2, 15, 17, 2, 16, 
	4, 2, 16, 5, 2, 16, 6, 2, 
	16, 7, 2, 16, 8, 2, 16, 14, 
	2, 18, 19, 2, 20, 0, 2, 20, 
	2, 2, 20, 14, 2, 20, 17, 3, 
	3, 12, 13, 3, 9, 12, 13, 3, 
	10, 12, 13, 3, 11, 12, 13, 3, 
	12, 13, 16, 3, 15, 12, 13, 4, 
	1, 12, 13, 16, 4, 15, 0, 12, 
	13
};

static const short _lexer_key_offsets[] = {
	0, 0, 18, 19, 21, 22, 23, 24, 
	26, 43, 44, 45, 49, 54, 59, 64, 
	69, 73, 77, 79, 80, 81, 82, 83, 
	84, 85, 86, 87, 88, 89, 90, 91, 
	92, 93, 94, 99, 106, 111, 112, 113, 
	114, 115, 116, 117, 118, 119, 120, 121, 
	122, 124, 125, 126, 140, 142, 145, 147, 
	149, 166, 167, 168, 169, 170, 171, 172, 
	173, 174, 175, 176, 177, 178, 191, 193, 
	196, 198, 200, 202, 204, 206, 208, 210, 
	212, 214, 216, 218, 220, 222, 224, 226, 
	228, 230, 232, 234, 236, 238, 240, 242, 
	244, 246, 248, 250, 252, 254, 256, 258, 
	262, 264, 266, 268, 270, 272, 274, 276, 
	278, 280, 282, 284, 286, 288, 290, 292, 
	295, 297, 299, 301, 303, 305, 307, 309, 
	311, 313, 315, 317, 319, 321, 323, 325, 
	327, 329, 331, 332, 333, 334, 335, 336, 
	337, 338, 345, 347, 349, 351, 353, 355, 
	357, 359, 361, 363, 365, 368, 369, 370, 
	371, 372, 373, 374, 375, 376, 377, 378, 
	379, 391, 393, 395, 397, 399, 401, 403, 
	405, 407, 409, 411, 413, 415, 417, 419, 
	421, 423, 425, 427, 429, 431, 433, 435, 
	437, 439, 441, 444, 446, 448, 450, 452, 
	454, 456, 458, 460, 462, 464, 466, 468, 
	470, 472, 474, 476, 478, 480, 482, 484, 
	486, 488, 490, 492, 494, 496, 498, 500, 
	502, 505, 507, 509, 511, 513, 515, 517, 
	519, 521, 523, 525, 527, 529, 531, 533, 
	535, 537, 538, 539, 540, 541, 542, 543, 
	544, 546, 547, 548, 549, 550, 551, 552, 
	553, 554, 555, 556, 557, 558, 559, 560, 
	561, 562, 574, 576, 579, 581, 583, 585, 
	587, 589, 591, 593, 595, 597, 599, 601, 
	603, 605, 607, 609, 611, 613, 615, 617, 
	619, 621, 623, 625, 627, 629, 631, 633, 
	635, 637, 639, 641, 645, 647, 649, 651, 
	653, 655, 657, 659, 661, 663, 664, 665, 
	666, 667, 668, 672, 678, 681, 683, 689, 
	706, 708, 710, 712, 714, 716, 718, 720, 
	722, 724, 726, 728, 730, 732, 734, 736, 
	738, 740, 742, 744, 746, 748, 750, 752, 
	754, 756, 758, 760, 762, 764, 766, 768, 
	770, 772, 774, 776, 778, 780, 782, 786, 
	788, 790, 792, 794, 796, 798, 800, 802, 
	804, 806, 808, 810, 812, 814, 816, 819, 
	821, 823, 825, 827, 829, 831, 833, 835, 
	837, 839, 841, 843, 845, 847, 849, 851, 
	853, 854, 855, 856, 857, 858, 859
};

static const char _lexer_trans_keys[] = {
	-61, -17, 10, 32, 34, 35, 37, 42, 
	64, 65, 66, 68, 69, 76, 79, 124, 
	9, 13, -98, -61, 101, -95, 32, 10, 
	10, 13, -61, 10, 32, 34, 35, 37, 
	42, 64, 65, 66, 68, 69, 76, 79, 
	124, 9, 13, 34, 34, 10, 32, 9, 
	13, 10, 32, 34, 9, 13, 10, 32, 
	34, 9, 13, 10, 32, 34, 9, 13, 
	10, 32, 34, 9, 13, 10, 32, 9, 
	13, 10, 32, 9, 13, 10, 13, 10, 
	95, 70, 69, 65, 84, 85, 82, 69, 
	95, 69, 78, 68, 95, 37, 13, 32, 
	64, 9, 10, 9, 10, 13, 32, 64, 
	11, 12, 10, 32, 64, 9, 13, 116, 
	98, 117, 114, -61, -80, 97, 114, -61, 
	-95, 115, 58, 105, 10, 10, -61, 10, 
	32, 35, 37, 42, 64, 65, 66, 69, 
	76, 79, 9, 13, -98, 10, -61, 10, 
	101, -95, 10, 10, 32, -61, 10, 32, 
	34, 35, 37, 42, 64, 65, 66, 68, 
	69, 76, 79, 124, 9, 13, 97, 107, 
	103, 114, 117, 110, 110, 117, 114, 58, 
	10, 10, -61, 10, 32, 35, 37, 42, 
	64, 65, 69, 76, 79, 9, 13, -98, 
	10, -61, 10, 101, -95, 10, 10, 32, 
	10, 103, 10, 97, 10, 114, 10, 95, 
	10, 70, 10, 69, 10, 65, 10, 84, 
	10, 85, 10, 82, 10, 69, 10, 95, 
	10, 69, 10, 78, 10, 68, 10, 95, 
	10, 37, 10, 116, 10, 98, 10, 117, 
	10, 114, -61, 10, -80, 10, 10, 97, 
	10, 114, -61, 10, -95, 10, 10, 115, 
	10, 58, 10, 102, 105, 110, 10, 103, 
	10, 105, 10, 110, 10, 108, 10, 101, 
	10, 105, 10, 107, 10, 105, -61, 10, 
	-67, 10, 10, 115, 10, 105, 10, 110, 
	10, 103, 10, 32, 10, 65, 68, 10, 
	116, 10, 98, 10, 117, 10, 114, -61, 
	10, -80, 10, 10, 97, 10, 114, -61, 
	10, -95, 10, 10, 115, 10, 97, 10, 
	114, -61, 10, -90, 10, 10, 109, 10, 
	97, 10, 103, -61, -90, 109, 105, 58, 
	10, 10, 10, 32, 35, 69, 124, 9, 
	13, 10, 105, 10, 103, 10, 105, 10, 
	110, 10, 108, 10, 101, 10, 105, 10, 
	107, 10, 105, 10, 58, 102, 105, 110, 
	103, 105, 110, 108, 101, 105, 107, 105, 
	58, 10, 10, 10, 32, 35, 37, 64, 
	65, 66, 68, 69, 76, 9, 13, 10, 
	95, 10, 70, 10, 69, 10, 65, 10, 
	84, 10, 85, 10, 82, 10, 69, 10, 
	95, 10, 69, 10, 78, 10, 68, 10, 
	95, 10, 37, 10, 116, 10, 98, 10, 
	117, 10, 114, -61, 10, -80, 10, 10, 
	97, 10, 114, -61, 10, -95, 10, 10, 
	115, 10, 58, 105, 10, 114, 10, 58, 
	10, 97, 10, 107, 10, 103, 10, 114, 
	10, 117, 10, 110, 10, 110, 10, 117, 
	-61, 10, -90, 10, 10, 109, 10, 105, 
	10, 105, 10, 103, 10, 105, 10, 110, 
	10, 108, 10, 101, 10, 105, 10, 107, 
	-61, 10, -67, 10, 10, 115, 10, 105, 
	10, 110, 10, 103, 10, 32, 10, 65, 
	68, 10, 116, 10, 98, 10, 117, 10, 
	114, -61, 10, -80, 10, 10, 97, 10, 
	114, -61, 10, -95, 10, 10, 115, 10, 
	97, -61, 10, -90, 10, 10, 109, 10, 
	97, -61, -67, 115, 105, 110, 103, 32, 
	65, 68, 116, 98, 117, 114, -61, -80, 
	97, 114, -61, -95, 115, 97, 114, 58, 
	10, 10, -61, 10, 32, 35, 37, 42, 
	64, 65, 69, 79, 9, 13, -98, 10, 
	-61, 10, 101, -95, 10, 10, 32, 10, 
	103, 10, 97, 10, 114, 10, 95, 10, 
	70, 10, 69, 10, 65, 10, 84, 10, 
	85, 10, 82, 10, 69, 10, 95, 10, 
	69, 10, 78, 10, 68, 10, 95, 10, 
	37, 10, 116, 10, 98, 10, 117, 10, 
	114, -61, 10, -80, 10, 10, 97, 10, 
	114, -61, 10, -95, 10, 10, 115, 10, 
	58, 10, 102, 105, 110, 10, 103, 10, 
	105, 10, 110, 10, 108, 10, 101, 10, 
	105, 10, 107, 10, 105, 10, 103, -61, 
	-90, 109, 97, 103, 32, 124, 9, 13, 
	10, 32, 92, 124, 9, 13, 10, 92, 
	124, 10, 92, 10, 32, 92, 124, 9, 
	13, -61, 10, 32, 34, 35, 37, 42, 
	64, 65, 66, 68, 69, 76, 79, 124, 
	9, 13, 10, 103, 10, 97, 10, 114, 
	10, 95, 10, 70, 10, 69, 10, 65, 
	10, 84, 10, 85, 10, 82, 10, 69, 
	10, 95, 10, 69, 10, 78, 10, 68, 
	10, 95, 10, 37, 10, 116, 10, 98, 
	10, 117, 10, 114, -61, 10, -80, 10, 
	10, 97, 10, 114, -61, 10, -95, 10, 
	10, 115, 10, 58, 10, 97, 10, 107, 
	10, 103, 10, 114, 10, 117, 10, 110, 
	10, 110, 10, 117, 10, 114, 10, 102, 
	105, 110, 10, 103, 10, 105, 10, 110, 
	10, 108, 10, 101, 10, 105, 10, 107, 
	10, 105, -61, 10, -67, 10, 10, 115, 
	10, 105, 10, 110, 10, 103, 10, 32, 
	10, 65, 68, 10, 116, 10, 98, 10, 
	117, 10, 114, -61, 10, -80, 10, 10, 
	97, 10, 114, -61, 10, -95, 10, 10, 
	115, 10, 97, -61, 10, -90, 10, 10, 
	109, 10, 97, 10, 103, 114, 103, 97, 
	114, -69, -65, 0
};

static const char _lexer_single_lengths[] = {
	0, 16, 1, 2, 1, 1, 1, 2, 
	15, 1, 1, 2, 3, 3, 3, 3, 
	2, 2, 2, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 3, 5, 3, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	2, 1, 1, 12, 2, 3, 2, 2, 
	15, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 11, 2, 3, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 4, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 3, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 1, 1, 1, 1, 1, 1, 
	1, 5, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 3, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	10, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 3, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	3, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 1, 1, 1, 1, 1, 1, 1, 
	2, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	1, 10, 2, 3, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 4, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 1, 1, 1, 
	1, 1, 2, 4, 3, 2, 4, 15, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 4, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 3, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	1, 1, 1, 1, 1, 1, 0
};

static const char _lexer_range_lengths[] = {
	0, 1, 0, 0, 0, 0, 0, 0, 
	1, 0, 0, 1, 1, 1, 1, 1, 
	1, 1, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 1, 1, 1, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 1, 0, 0, 0, 0, 
	1, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 1, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 1, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	1, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 1, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 1, 1, 0, 0, 1, 1, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0
};

static const short _lexer_index_offsets[] = {
	0, 0, 18, 20, 23, 25, 27, 29, 
	32, 49, 51, 53, 57, 62, 67, 72, 
	77, 81, 85, 88, 90, 92, 94, 96, 
	98, 100, 102, 104, 106, 108, 110, 112, 
	114, 116, 118, 123, 130, 135, 137, 139, 
	141, 143, 145, 147, 149, 151, 153, 155, 
	157, 160, 162, 164, 178, 181, 185, 188, 
	191, 208, 210, 212, 214, 216, 218, 220, 
	222, 224, 226, 228, 230, 232, 245, 248, 
	252, 255, 258, 261, 264, 267, 270, 273, 
	276, 279, 282, 285, 288, 291, 294, 297, 
	300, 303, 306, 309, 312, 315, 318, 321, 
	324, 327, 330, 333, 336, 339, 342, 345, 
	350, 353, 356, 359, 362, 365, 368, 371, 
	374, 377, 380, 383, 386, 389, 392, 395, 
	399, 402, 405, 408, 411, 414, 417, 420, 
	423, 426, 429, 432, 435, 438, 441, 444, 
	447, 450, 453, 455, 457, 459, 461, 463, 
	465, 467, 474, 477, 480, 483, 486, 489, 
	492, 495, 498, 501, 504, 508, 510, 512, 
	514, 516, 518, 520, 522, 524, 526, 528, 
	530, 542, 545, 548, 551, 554, 557, 560, 
	563, 566, 569, 572, 575, 578, 581, 584, 
	587, 590, 593, 596, 599, 602, 605, 608, 
	611, 614, 617, 621, 624, 627, 630, 633, 
	636, 639, 642, 645, 648, 651, 654, 657, 
	660, 663, 666, 669, 672, 675, 678, 681, 
	684, 687, 690, 693, 696, 699, 702, 705, 
	708, 712, 715, 718, 721, 724, 727, 730, 
	733, 736, 739, 742, 745, 748, 751, 754, 
	757, 760, 762, 764, 766, 768, 770, 772, 
	774, 777, 779, 781, 783, 785, 787, 789, 
	791, 793, 795, 797, 799, 801, 803, 805, 
	807, 809, 821, 824, 828, 831, 834, 837, 
	840, 843, 846, 849, 852, 855, 858, 861, 
	864, 867, 870, 873, 876, 879, 882, 885, 
	888, 891, 894, 897, 900, 903, 906, 909, 
	912, 915, 918, 921, 926, 929, 932, 935, 
	938, 941, 944, 947, 950, 953, 955, 957, 
	959, 961, 963, 967, 973, 977, 980, 986, 
	1003, 1006, 1009, 1012, 1015, 1018, 1021, 1024, 
	1027, 1030, 1033, 1036, 1039, 1042, 1045, 1048, 
	1051, 1054, 1057, 1060, 1063, 1066, 1069, 1072, 
	1075, 1078, 1081, 1084, 1087, 1090, 1093, 1096, 
	1099, 1102, 1105, 1108, 1111, 1114, 1117, 1122, 
	1125, 1128, 1131, 1134, 1137, 1140, 1143, 1146, 
	1149, 1152, 1155, 1158, 1161, 1164, 1167, 1171, 
	1174, 1177, 1180, 1183, 1186, 1189, 1192, 1195, 
	1198, 1201, 1204, 1207, 1210, 1213, 1216, 1219, 
	1222, 1224, 1226, 1228, 1230, 1232, 1234
};

static const short _lexer_trans_targs[] = {
	2, 396, 8, 8, 9, 18, 20, 5, 
	34, 37, 57, 138, 156, 241, 313, 314, 
	8, 0, 3, 0, 4, 393, 0, 5, 
	0, 6, 0, 0, 7, 8, 19, 7, 
	2, 8, 8, 9, 18, 20, 5, 34, 
	37, 57, 138, 156, 241, 313, 314, 8, 
	0, 10, 0, 11, 0, 12, 11, 11, 
	0, 13, 13, 14, 13, 13, 13, 13, 
	14, 13, 13, 13, 13, 15, 13, 13, 
	13, 13, 16, 13, 13, 8, 17, 17, 
	0, 8, 17, 17, 0, 8, 19, 18, 
	8, 0, 21, 0, 22, 0, 23, 0, 
	24, 0, 25, 0, 26, 0, 27, 0, 
	28, 0, 29, 0, 30, 0, 31, 0, 
	32, 0, 33, 0, 398, 0, 0, 0, 
	0, 0, 35, 36, 8, 36, 36, 34, 
	35, 35, 8, 36, 34, 36, 0, 38, 
	0, 39, 0, 40, 0, 41, 0, 42, 
	0, 43, 0, 44, 0, 45, 0, 46, 
	0, 47, 0, 48, 0, 49, 392, 0, 
	51, 50, 51, 50, 52, 51, 51, 8, 
	323, 55, 8, 337, 349, 358, 367, 391, 
	51, 50, 53, 51, 50, 54, 51, 320, 
	50, 55, 51, 50, 51, 56, 50, 2, 
	8, 8, 9, 18, 20, 5, 34, 37, 
	57, 138, 156, 241, 313, 314, 8, 0, 
	58, 0, 59, 0, 60, 0, 61, 0, 
	62, 0, 63, 0, 64, 0, 65, 0, 
	66, 0, 67, 0, 69, 68, 69, 68, 
	70, 69, 69, 8, 77, 73, 8, 91, 
	103, 112, 137, 69, 68, 71, 69, 68, 
	72, 69, 74, 68, 73, 69, 68, 69, 
	56, 68, 69, 75, 68, 69, 76, 68, 
	69, 73, 68, 69, 78, 68, 69, 79, 
	68, 69, 80, 68, 69, 81, 68, 69, 
	82, 68, 69, 83, 68, 69, 84, 68, 
	69, 85, 68, 69, 86, 68, 69, 87, 
	68, 69, 88, 68, 69, 89, 68, 69, 
	90, 68, 69, 8, 68, 69, 92, 68, 
	69, 93, 68, 69, 94, 68, 69, 95, 
	68, 96, 69, 68, 97, 69, 68, 69, 
	98, 68, 69, 99, 68, 100, 69, 68, 
	101, 69, 68, 69, 102, 68, 69, 56, 
	68, 69, 73, 104, 73, 68, 69, 105, 
	68, 69, 106, 68, 69, 107, 68, 69, 
	108, 68, 69, 109, 68, 69, 110, 68, 
	69, 111, 68, 69, 102, 68, 113, 69, 
	68, 114, 69, 68, 69, 115, 68, 69, 
	116, 68, 69, 117, 68, 69, 118, 68, 
	69, 119, 68, 69, 120, 133, 68, 69, 
	121, 68, 69, 122, 68, 69, 123, 68, 
	69, 124, 68, 125, 69, 68, 126, 69, 
	68, 69, 127, 68, 69, 128, 68, 129, 
	69, 68, 130, 69, 68, 69, 131, 68, 
	69, 132, 68, 69, 102, 68, 134, 69, 
	68, 135, 69, 68, 69, 136, 68, 69, 
	102, 68, 69, 73, 68, 139, 0, 140, 
	0, 141, 0, 142, 0, 143, 0, 145, 
	144, 145, 144, 145, 145, 8, 146, 8, 
	145, 144, 145, 147, 144, 145, 148, 144, 
	145, 149, 144, 145, 150, 144, 145, 151, 
	144, 145, 152, 144, 145, 153, 144, 145, 
	154, 144, 145, 155, 144, 145, 56, 144, 
	5, 157, 5, 0, 158, 0, 159, 0, 
	160, 0, 161, 0, 162, 0, 163, 0, 
	164, 0, 165, 0, 166, 0, 168, 167, 
	168, 167, 168, 168, 8, 169, 8, 183, 
	197, 205, 209, 217, 168, 167, 168, 170, 
	167, 168, 171, 167, 168, 172, 167, 168, 
	173, 167, 168, 174, 167, 168, 175, 167, 
	168, 176, 167, 168, 177, 167, 168, 178, 
	167, 168, 179, 167, 168, 180, 167, 168, 
	181, 167, 168, 182, 167, 168, 8, 167, 
	168, 184, 167, 168, 185, 167, 168, 186, 
	167, 168, 187, 167, 188, 168, 167, 189, 
	168, 167, 168, 190, 167, 168, 191, 167, 
	192, 168, 167, 193, 168, 167, 168, 194, 
	167, 168, 56, 195, 167, 168, 196, 167, 
	168, 56, 167, 168, 198, 167, 168, 199, 
	167, 168, 200, 167, 168, 201, 167, 168, 
	202, 167, 168, 203, 167, 168, 204, 167, 
	168, 195, 167, 206, 168, 167, 207, 168, 
	167, 168, 208, 167, 168, 196, 167, 168, 
	210, 167, 168, 211, 167, 168, 212, 167, 
	168, 213, 167, 168, 214, 167, 168, 215, 
	167, 168, 216, 167, 168, 208, 167, 218, 
	168, 167, 219, 168, 167, 168, 220, 167, 
	168, 221, 167, 168, 222, 167, 168, 223, 
	167, 168, 224, 167, 168, 225, 237, 167, 
	168, 226, 167, 168, 227, 167, 168, 228, 
	167, 168, 229, 167, 230, 168, 167, 231, 
	168, 167, 168, 232, 167, 168, 233, 167, 
	234, 168, 167, 235, 168, 167, 168, 236, 
	167, 168, 195, 167, 238, 168, 167, 239, 
	168, 167, 168, 240, 167, 168, 196, 167, 
	242, 0, 243, 0, 244, 0, 245, 0, 
	246, 0, 247, 0, 248, 0, 249, 309, 
	0, 250, 0, 251, 0, 252, 0, 253, 
	0, 254, 0, 255, 0, 256, 0, 257, 
	0, 258, 0, 259, 0, 260, 0, 261, 
	0, 262, 0, 263, 0, 265, 264, 265, 
	264, 266, 265, 265, 8, 273, 269, 8, 
	287, 299, 308, 265, 264, 267, 265, 264, 
	268, 265, 270, 264, 269, 265, 264, 265, 
	56, 264, 265, 271, 264, 265, 272, 264, 
	265, 269, 264, 265, 274, 264, 265, 275, 
	264, 265, 276, 264, 265, 277, 264, 265, 
	278, 264, 265, 279, 264, 265, 280, 264, 
	265, 281, 264, 265, 282, 264, 265, 283, 
	264, 265, 284, 264, 265, 285, 264, 265, 
	286, 264, 265, 8, 264, 265, 288, 264, 
	265, 289, 264, 265, 290, 264, 265, 291, 
	264, 292, 265, 264, 293, 265, 264, 265, 
	294, 264, 265, 295, 264, 296, 265, 264, 
	297, 265, 264, 265, 298, 264, 265, 56, 
	264, 265, 269, 300, 269, 264, 265, 301, 
	264, 265, 302, 264, 265, 303, 264, 265, 
	304, 264, 265, 305, 264, 265, 306, 264, 
	265, 307, 264, 265, 298, 264, 265, 269, 
	264, 310, 0, 311, 0, 312, 0, 262, 
	0, 5, 0, 314, 315, 314, 0, 319, 
	318, 317, 315, 318, 316, 0, 317, 315, 
	316, 0, 317, 316, 319, 318, 317, 315, 
	318, 316, 2, 319, 319, 9, 18, 20, 
	5, 34, 37, 57, 138, 156, 241, 313, 
	314, 319, 0, 51, 321, 50, 51, 322, 
	50, 51, 55, 50, 51, 324, 50, 51, 
	325, 50, 51, 326, 50, 51, 327, 50, 
	51, 328, 50, 51, 329, 50, 51, 330, 
	50, 51, 331, 50, 51, 332, 50, 51, 
	333, 50, 51, 334, 50, 51, 335, 50, 
	51, 336, 50, 51, 8, 50, 51, 338, 
	50, 51, 339, 50, 51, 340, 50, 51, 
	341, 50, 342, 51, 50, 343, 51, 50, 
	51, 344, 50, 51, 345, 50, 346, 51, 
	50, 347, 51, 50, 51, 348, 50, 51, 
	56, 50, 51, 350, 50, 51, 351, 50, 
	51, 352, 50, 51, 353, 50, 51, 354, 
	50, 51, 355, 50, 51, 356, 50, 51, 
	357, 50, 51, 348, 50, 51, 55, 359, 
	55, 50, 51, 360, 50, 51, 361, 50, 
	51, 362, 50, 51, 363, 50, 51, 364, 
	50, 51, 365, 50, 51, 366, 50, 51, 
	348, 50, 368, 51, 50, 369, 51, 50, 
	51, 370, 50, 51, 371, 50, 51, 372, 
	50, 51, 373, 50, 51, 374, 50, 51, 
	375, 387, 50, 51, 376, 50, 51, 377, 
	50, 51, 378, 50, 51, 379, 50, 380, 
	51, 50, 381, 51, 50, 51, 382, 50, 
	51, 383, 50, 384, 51, 50, 385, 51, 
	50, 51, 386, 50, 51, 357, 50, 388, 
	51, 50, 389, 51, 50, 51, 390, 50, 
	51, 348, 50, 51, 55, 50, 142, 0, 
	394, 0, 395, 0, 5, 0, 397, 0, 
	8, 0, 0, 0
};

static const char _lexer_trans_actions[] = {
	25, 0, 47, 0, 5, 1, 0, 25, 
	1, 25, 25, 25, 25, 25, 25, 31, 
	0, 39, 0, 39, 0, 0, 39, 0, 
	39, 0, 39, 39, 50, 99, 19, 0, 
	25, 47, 0, 5, 1, 0, 25, 1, 
	25, 25, 25, 25, 25, 25, 31, 0, 
	39, 0, 39, 0, 39, 47, 0, 0, 
	39, 119, 41, 41, 41, 3, 111, 29, 
	29, 29, 0, 111, 29, 29, 29, 0, 
	111, 29, 0, 29, 0, 95, 7, 7, 
	39, 47, 0, 0, 39, 103, 21, 0, 
	47, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 0, 39, 39, 39, 
	39, 39, 0, 23, 107, 23, 23, 44, 
	23, 0, 47, 0, 1, 0, 39, 0, 
	39, 0, 39, 0, 39, 0, 39, 0, 
	39, 0, 39, 0, 39, 0, 39, 0, 
	39, 0, 39, 0, 39, 0, 0, 39, 
	124, 50, 47, 0, 77, 47, 0, 68, 
	29, 77, 68, 77, 77, 77, 77, 77, 
	0, 0, 0, 47, 0, 0, 47, 0, 
	0, 0, 47, 0, 47, 13, 0, 56, 
	115, 27, 53, 50, 27, 56, 50, 56, 
	56, 56, 56, 56, 56, 59, 27, 39, 
	0, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 124, 50, 47, 0, 
	77, 47, 0, 65, 29, 77, 65, 77, 
	77, 77, 77, 0, 0, 0, 47, 0, 
	0, 47, 0, 0, 0, 47, 0, 47, 
	11, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 11, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 0, 47, 0, 0, 47, 0, 47, 
	0, 0, 47, 0, 0, 0, 47, 0, 
	0, 47, 0, 47, 0, 0, 47, 11, 
	0, 47, 0, 0, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 0, 47, 
	0, 0, 47, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 0, 47, 0, 0, 47, 
	0, 47, 0, 0, 47, 0, 0, 0, 
	47, 0, 0, 47, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 0, 47, 
	0, 0, 47, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 0, 39, 0, 
	39, 0, 39, 0, 39, 0, 39, 124, 
	50, 47, 0, 47, 0, 74, 77, 74, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 17, 0, 
	0, 0, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 0, 39, 124, 50, 
	47, 0, 47, 0, 62, 29, 62, 77, 
	77, 77, 77, 77, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 9, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 0, 47, 0, 0, 
	47, 0, 47, 0, 0, 47, 0, 0, 
	0, 47, 0, 0, 47, 0, 47, 0, 
	0, 47, 9, 0, 0, 47, 0, 0, 
	47, 9, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 0, 47, 0, 0, 47, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 0, 
	47, 0, 0, 47, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 0, 47, 0, 0, 
	47, 0, 47, 0, 0, 47, 0, 0, 
	0, 47, 0, 0, 47, 0, 47, 0, 
	0, 47, 0, 0, 0, 47, 0, 0, 
	47, 0, 47, 0, 0, 47, 0, 0, 
	0, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 39, 0, 39, 0, 0, 
	39, 0, 39, 0, 39, 0, 39, 0, 
	39, 0, 39, 0, 39, 0, 39, 0, 
	39, 0, 39, 0, 39, 0, 39, 0, 
	39, 0, 39, 0, 39, 124, 50, 47, 
	0, 77, 47, 0, 71, 29, 77, 71, 
	77, 77, 77, 0, 0, 0, 47, 0, 
	0, 47, 0, 0, 0, 47, 0, 47, 
	15, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 15, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 0, 47, 0, 0, 47, 0, 47, 
	0, 0, 47, 0, 0, 0, 47, 0, 
	0, 47, 0, 47, 0, 0, 47, 15, 
	0, 47, 0, 0, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 0, 39, 0, 39, 0, 39, 0, 
	39, 0, 39, 0, 0, 0, 39, 47, 
	33, 33, 80, 33, 33, 39, 0, 35, 
	0, 39, 0, 0, 47, 0, 0, 35, 
	0, 0, 89, 47, 0, 86, 83, 37, 
	89, 83, 89, 89, 89, 89, 89, 89, 
	92, 0, 39, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 13, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 0, 47, 0, 0, 47, 0, 
	47, 0, 0, 47, 0, 0, 0, 47, 
	0, 0, 47, 0, 47, 0, 0, 47, 
	13, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	0, 0, 47, 0, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 0, 47, 0, 0, 47, 0, 
	47, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 47, 
	0, 0, 0, 47, 0, 0, 47, 0, 
	0, 47, 0, 0, 47, 0, 0, 0, 
	47, 0, 0, 47, 0, 47, 0, 0, 
	47, 0, 0, 0, 47, 0, 0, 47, 
	0, 47, 0, 0, 47, 0, 0, 0, 
	47, 0, 0, 47, 0, 47, 0, 0, 
	47, 0, 0, 47, 0, 0, 0, 39, 
	0, 39, 0, 39, 0, 39, 0, 39, 
	0, 39, 0, 0
};

static const char _lexer_eof_actions[] = {
	0, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39
};

static const int lexer_start = 1;
static const int lexer_first_final = 398;
static const int lexer_error = 0;

static const int lexer_en_main = 1;


#line 246 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"

static VALUE 
unindent(VALUE con, int start_col)
{
  VALUE re;
  // Gherkin will crash gracefully if the string representation of start_col pushes the pattern past 32 characters
  char pat[32]; 
  snprintf(pat, 32, "^[\t ]{0,%d}", start_col); 
  re = rb_reg_regcomp(rb_str_new2(pat));
  rb_funcall(con, rb_intern("gsub!"), 2, re, rb_str_new2(""));

  return Qnil;

}

static void 
store_kw_con(VALUE listener, const char * event_name, 
             const char * keyword_at, size_t keyword_length, 
             const char * at,         size_t length, 
             int current_line)
{
  VALUE con = Qnil, kw = Qnil;
  kw = ENCODED_STR_NEW(keyword_at, keyword_length);
  con = ENCODED_STR_NEW(at, length);
  rb_funcall(con, rb_intern("strip!"), 0);
  rb_funcall(listener, rb_intern(event_name), 3, kw, con, INT2FIX(current_line)); 
}

static void
store_multiline_kw_con(VALUE listener, const char * event_name,
                      const char * keyword_at, size_t keyword_length,
                      const char * at,         size_t length,
                      int current_line, int start_col)
{
  VALUE split;
  VALUE con = Qnil, kw = Qnil, name = Qnil, desc = Qnil;

  kw = ENCODED_STR_NEW(keyword_at, keyword_length);
  con = ENCODED_STR_NEW(at, length);

  unindent(con, start_col);
  
  split = rb_str_split(con, "\n");

  name = rb_funcall(split, rb_intern("shift"), 0);
  desc = rb_ary_join(split, rb_str_new2( "\n" ));

  if( name == Qnil ) 
  {
    name = rb_str_new2("");
  }
  if( rb_funcall(desc, rb_intern("size"), 0) == 0) 
  {
    desc = rb_str_new2("");
  }
  rb_funcall(name, rb_intern("strip!"), 0);
  rb_funcall(desc, rb_intern("rstrip!"), 0);
  rb_funcall(listener, rb_intern(event_name), 4, kw, name, desc, INT2FIX(current_line)); 
}

static void 
store_attr(VALUE listener, const char * attr_type,
           const char * at, size_t length, 
           int line)
{
  VALUE val = ENCODED_STR_NEW(at, length);
  rb_funcall(listener, rb_intern(attr_type), 2, val, INT2FIX(line));
}

static void 
store_pystring_content(VALUE listener, 
          int start_col, 
          const char *at, size_t length, 
          int current_line)
{
  VALUE re2;
  VALUE unescape_escaped_quotes;
  VALUE con = ENCODED_STR_NEW(at, length);

  unindent(con, start_col);

  re2 = rb_reg_regcomp(rb_str_new2("\r\\Z"));
  unescape_escaped_quotes = rb_reg_regcomp(rb_str_new2("\\\\\"\\\\\"\\\\\""));
  rb_funcall(con, rb_intern("sub!"), 2, re2, rb_str_new2(""));
  rb_funcall(con, rb_intern("gsub!"), 2, unescape_escaped_quotes, rb_str_new2("\"\"\""));
  rb_funcall(listener, rb_intern("doc_string"), 2, con, INT2FIX(current_line));
}

static void 
raise_lexer_error(const char * at, int line)
{ 
  rb_raise(rb_eGherkinLexingError, "Lexing error on line %d: '%s'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.", line, at);
}

static void lexer_init(lexer_state *lexer) {
  lexer->content_start = 0;
  lexer->content_end = 0;
  lexer->content_len = 0;
  lexer->mark = 0;
  lexer->keyword_start = 0;
  lexer->keyword_end = 0;
  lexer->next_keyword_start = 0;
  lexer->line_number = 1;
  lexer->last_newline = 0;
  lexer->final_newline = 0;
  lexer->start_col = 0;
}

static VALUE CLexer_alloc(VALUE klass)
{
  VALUE obj;
  lexer_state *lxr = ALLOC(lexer_state);
  lexer_init(lxr);

  obj = Data_Wrap_Struct(klass, NULL, -1, lxr);

  return obj;
}

static VALUE CLexer_init(VALUE self, VALUE listener)
{
  lexer_state *lxr; 
  rb_iv_set(self, "@listener", listener);
  
  lxr = NULL;
  DATA_GET(self, lexer_state, lxr);
  lexer_init(lxr);
  
  return self;
}

static VALUE CLexer_scan(VALUE self, VALUE input)
{
  VALUE input_copy;
  char *data;
  size_t len;
  VALUE listener = rb_iv_get(self, "@listener");

  lexer_state *lexer;
  lexer = NULL;
  DATA_GET(self, lexer_state, lexer);

  input_copy = rb_str_dup(input);

  rb_str_append(input_copy, rb_str_new2("\n%_FEATURE_END_%"));
  data = RSTRING_PTR(input_copy);
  len = RSTRING_LEN(input_copy);
  
  if (len == 0) { 
    rb_raise(rb_eGherkinLexingError, "No content to lex.");
  } else {

    const char *p, *pe, *eof;
    int cs = 0;
    
    VALUE current_row = Qnil;

    p = data;
    pe = data + len;
    eof = pe;
    
    assert(*pe == '\0' && "pointer does not end on NULL");
    
    
#line 972 "ext/gherkin_lexer_is/gherkin_lexer_is.c"
	{
	cs = lexer_start;
	}

#line 410 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
    
#line 979 "ext/gherkin_lexer_is/gherkin_lexer_is.c"
	{
	int _klen;
	unsigned int _trans;
	const char *_acts;
	unsigned int _nacts;
	const char *_keys;

	if ( p == pe )
		goto _test_eof;
	if ( cs == 0 )
		goto _out;
_resume:
	_keys = _lexer_trans_keys + _lexer_key_offsets[cs];
	_trans = _lexer_index_offsets[cs];

	_klen = _lexer_single_lengths[cs];
	if ( _klen > 0 ) {
		const char *_lower = _keys;
		const char *_mid;
		const char *_upper = _keys + _klen - 1;
		while (1) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( (*p) < *_mid )
				_upper = _mid - 1;
			else if ( (*p) > *_mid )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				goto _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _lexer_range_lengths[cs];
	if ( _klen > 0 ) {
		const char *_lower = _keys;
		const char *_mid;
		const char *_upper = _keys + (_klen<<1) - 2;
		while (1) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( (*p) < _mid[0] )
				_upper = _mid - 2;
			else if ( (*p) > _mid[1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				goto _match;
			}
		}
		_trans += _klen;
	}

_match:
	cs = _lexer_trans_targs[_trans];

	if ( _lexer_trans_actions[_trans] == 0 )
		goto _again;

	_acts = _lexer_actions + _lexer_trans_actions[_trans];
	_nacts = (unsigned int) *_acts++;
	while ( _nacts-- > 0 )
	{
		switch ( *_acts++ )
		{
	case 0:
#line 81 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
		MARK(content_start, p);
    lexer->current_line = lexer->line_number;
    lexer->start_col = lexer->content_start - lexer->last_newline - (lexer->keyword_end - lexer->keyword_start) + 2;
  }
	break;
	case 1:
#line 87 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    MARK(content_start, p);
  }
	break;
	case 2:
#line 91 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    lexer->current_line = lexer->line_number;
    lexer->start_col = p - data - lexer->last_newline;
  }
	break;
	case 3:
#line 96 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    int len = LEN(content_start, PTR_TO(final_newline));

    if (len < 0) len = 0;

    store_pystring_content(listener, lexer->start_col, PTR_TO(content_start), len, lexer->current_line);
  }
	break;
	case 4:
#line 104 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    STORE_KW_END_CON(feature);
  }
	break;
	case 5:
#line 108 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    STORE_KW_END_CON(background);
  }
	break;
	case 6:
#line 112 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    STORE_KW_END_CON(scenario);
  }
	break;
	case 7:
#line 116 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    STORE_KW_END_CON(scenario_outline);
  }
	break;
	case 8:
#line 120 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    STORE_KW_END_CON(examples);
  }
	break;
	case 9:
#line 124 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    store_kw_con(listener, "step",
      PTR_TO(keyword_start), LEN(keyword_start, PTR_TO(keyword_end)),
      PTR_TO(content_start), LEN(content_start, p), 
      lexer->current_line);
  }
	break;
	case 10:
#line 131 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    STORE_ATTR(comment);
    lexer->mark = 0;
  }
	break;
	case 11:
#line 136 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    STORE_ATTR(tag);
    lexer->mark = 0;
  }
	break;
	case 12:
#line 141 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    lexer->line_number += 1;
    MARK(final_newline, p);
  }
	break;
	case 13:
#line 146 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    MARK(last_newline, p + 1);
  }
	break;
	case 14:
#line 150 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    if (lexer->mark == 0) {
      MARK(mark, p);
    }
  }
	break;
	case 15:
#line 156 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    MARK(keyword_end, p);
    MARK(keyword_start, PTR_TO(mark));
    MARK(content_start, p + 1);
    lexer->mark = 0;
  }
	break;
	case 16:
#line 163 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    MARK(content_end, p);
  }
	break;
	case 17:
#line 167 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    p = p - 1;
    lexer->current_line = lexer->line_number;
    current_row = rb_ary_new();
  }
	break;
	case 18:
#line 173 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
		MARK(content_start, p);
  }
	break;
	case 19:
#line 177 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    VALUE re_pipe, re_newline, re_backslash;
    VALUE con = ENCODED_STR_NEW(PTR_TO(content_start), LEN(content_start, p));
    rb_funcall(con, rb_intern("strip!"), 0);
    re_pipe      = rb_reg_regcomp(rb_str_new2("\\\\\\|"));
    re_newline   = rb_reg_regcomp(rb_str_new2("\\\\n"));
    re_backslash = rb_reg_regcomp(rb_str_new2("\\\\\\\\"));
    rb_funcall(con, rb_intern("gsub!"), 2, re_pipe,      rb_str_new2("|"));
    rb_funcall(con, rb_intern("gsub!"), 2, re_newline,   rb_str_new2("\n"));
    rb_funcall(con, rb_intern("gsub!"), 2, re_backslash, rb_str_new2("\\"));

    rb_ary_push(current_row, con);
  }
	break;
	case 20:
#line 191 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    rb_funcall(listener, rb_intern("row"), 2, current_row, INT2FIX(lexer->current_line));
  }
	break;
	case 21:
#line 195 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    int line;
    if (cs < lexer_first_final) {
      size_t count = 0;
      VALUE newstr_val;
      char *newstr;
      int newstr_count = 0;        
      size_t len;
      const char *buff;
      if (lexer->last_newline != 0) {
        len = LEN(last_newline, eof);
        buff = PTR_TO(last_newline);
      } else {
        len = strlen(data);
        buff = data;
      }

      // Allocate as a ruby string so that it gets cleaned up by GC
      newstr_val = rb_str_new(buff, len);
      newstr = RSTRING_PTR(newstr_val);


      for (count = 0; count < len; count++) {
        if(buff[count] == 10) {
          newstr[newstr_count] = '\0'; // terminate new string at first newline found
          break;
        } else {
          if (buff[count] == '%') {
            newstr[newstr_count++] = buff[count];
            newstr[newstr_count] = buff[count];
          } else {
            newstr[newstr_count] = buff[count];
          }
        }
        newstr_count++;
      }

      line = lexer->line_number;
      lexer_init(lexer); // Re-initialize so we can scan again with the same lexer
      raise_lexer_error(newstr, line);
    } else {
      rb_funcall(listener, rb_intern("eof"), 0);
    }
  }
	break;
#line 1255 "ext/gherkin_lexer_is/gherkin_lexer_is.c"
		}
	}

_again:
	if ( cs == 0 )
		goto _out;
	if ( ++p != pe )
		goto _resume;
	_test_eof: {}
	if ( p == eof )
	{
	const char *__acts = _lexer_actions + _lexer_eof_actions[cs];
	unsigned int __nacts = (unsigned int) *__acts++;
	while ( __nacts-- > 0 ) {
		switch ( *__acts++ ) {
	case 21:
#line 195 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"
	{
    int line;
    if (cs < lexer_first_final) {
      size_t count = 0;
      VALUE newstr_val;
      char *newstr;
      int newstr_count = 0;        
      size_t len;
      const char *buff;
      if (lexer->last_newline != 0) {
        len = LEN(last_newline, eof);
        buff = PTR_TO(last_newline);
      } else {
        len = strlen(data);
        buff = data;
      }

      // Allocate as a ruby string so that it gets cleaned up by GC
      newstr_val = rb_str_new(buff, len);
      newstr = RSTRING_PTR(newstr_val);


      for (count = 0; count < len; count++) {
        if(buff[count] == 10) {
          newstr[newstr_count] = '\0'; // terminate new string at first newline found
          break;
        } else {
          if (buff[count] == '%') {
            newstr[newstr_count++] = buff[count];
            newstr[newstr_count] = buff[count];
          } else {
            newstr[newstr_count] = buff[count];
          }
        }
        newstr_count++;
      }

      line = lexer->line_number;
      lexer_init(lexer); // Re-initialize so we can scan again with the same lexer
      raise_lexer_error(newstr, line);
    } else {
      rb_funcall(listener, rb_intern("eof"), 0);
    }
  }
	break;
#line 1318 "ext/gherkin_lexer_is/gherkin_lexer_is.c"
		}
	}
	}

	_out: {}
	}

#line 411 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/is.c.rl"

    assert(p <= pe && "data overflow after parsing execute");
    assert(lexer->content_start <= len && "content starts after data end");
    assert(lexer->mark < len && "mark is after data end");
    
    // Reset lexer by re-initializing the whole thing
    lexer_init(lexer);

    if (cs == lexer_error) {
      rb_raise(rb_eGherkinLexingError, "Invalid format, lexing fails.");
    } else {
      return Qtrue;
    }
  }
}

void Init_gherkin_lexer_is()
{
  mGherkin = rb_define_module("Gherkin");
  mGherkinLexer = rb_define_module_under(mGherkin, "Lexer");
  rb_eGherkinLexingError = rb_const_get(mGherkinLexer, rb_intern("LexingError"));

  mCLexer = rb_define_module_under(mGherkin, "CLexer");
  cI18nLexer = rb_define_class_under(mCLexer, "Is", rb_cObject);
  rb_define_alloc_func(cI18nLexer, CLexer_alloc);
  rb_define_method(cI18nLexer, "initialize", CLexer_init, 1);
  rb_define_method(cI18nLexer, "scan", CLexer_scan, 1);
}

