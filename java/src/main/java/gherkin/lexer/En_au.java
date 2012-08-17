
// line 1 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class En_au implements Lexer {
  
// line 150 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"


  private final Listener listener;

  public En_au(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/En_au.java"
private static byte[] init__lexer_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,
	   11,    1,   12,    1,   13,    1,   16,    1,   17,    1,   18,    1,
	   19,    1,   20,    1,   21,    1,   22,    1,   23,    2,    2,   18,
	    2,    3,    4,    2,   13,    0,    2,   14,   15,    2,   17,    0,
	    2,   17,    1,    2,   17,   16,    2,   17,   19,    2,   18,    6,
	    2,   18,    7,    2,   18,    8,    2,   18,    9,    2,   18,   10,
	    2,   18,   16,    2,   20,   21,    2,   22,    0,    2,   22,    1,
	    2,   22,   16,    2,   22,   19,    3,    4,   14,   15,    3,    5,
	   14,   15,    3,   11,   14,   15,    3,   12,   14,   15,    3,   13,
	   14,   15,    3,   14,   15,   18,    3,   17,    0,   11,    3,   17,
	   14,   15,    4,    2,   14,   15,   18,    4,    3,    4,   14,   15,
	    4,   17,    0,   14,   15,    5,   17,    0,   11,   14,   15
	};
}

private static final byte _lexer_actions[] = init__lexer_actions_0();


private static short[] init__lexer_key_offsets_0()
{
	return new short [] {
	    0,    0,   17,   18,   19,   35,   36,   37,   39,   41,   46,   51,
	   56,   61,   65,   69,   71,   72,   73,   74,   75,   76,   77,   78,
	   79,   80,   81,   82,   83,   84,   85,   86,   87,   89,   91,   96,
	  103,  108,  110,  111,  112,  113,  114,  115,  116,  117,  118,  119,
	  120,  121,  135,  137,  139,  141,  143,  145,  147,  149,  151,  153,
	  155,  157,  159,  161,  163,  165,  181,  184,  185,  186,  187,  188,
	  189,  190,  191,  192,  193,  200,  202,  204,  206,  208,  210,  212,
	  213,  214,  215,  216,  217,  218,  219,  229,  231,  233,  235,  237,
	  239,  241,  243,  245,  247,  249,  251,  253,  255,  257,  260,  262,
	  264,  266,  268,  270,  272,  274,  276,  278,  280,  282,  284,  286,
	  289,  291,  293,  295,  297,  299,  301,  303,  305,  307,  309,  311,
	  312,  313,  314,  315,  316,  317,  331,  333,  335,  337,  339,  341,
	  343,  345,  347,  349,  351,  353,  355,  357,  359,  361,  364,  366,
	  368,  370,  372,  374,  376,  378,  380,  382,  384,  386,  388,  390,
	  393,  395,  397,  399,  401,  403,  405,  407,  409,  411,  413,  415,
	  417,  419,  421,  424,  426,  428,  430,  432,  434,  436,  438,  440,
	  442,  444,  446,  447,  448,  449,  450,  451,  453,  454,  455,  456,
	  457,  458,  459,  460,  461,  462,  463,  464,  468,  474,  477,  479,
	  485,  501,  503,  505,  507,  509,  511,  513,  516,  518,  520,  522,
	  524,  526,  528,  530,  532,  534,  536,  538,  540,  542,  544,  547,
	  549,  551,  553,  555,  557,  559,  561,  563,  565,  567,  569,  570,
	  571,  572,  573,  574,  575,  576,  589,  591,  593,  595,  597,  599,
	  601,  603,  605,  607,  609,  611,  613,  615,  617,  619,  622,  624,
	  626,  628,  630,  632,  634,  636,  638,  640,  642,  644,  646,  648,
	  650,  652,  655,  657,  659,  661,  663,  665,  667,  669,  671,  673,
	  675,  677
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   66,   67,   77,   78,
	   87,   89,  124,    9,   13,  -69,  -65,   10,   32,   34,   35,   37,
	   42,   64,   66,   67,   77,   78,   87,   89,  124,    9,   13,   34,
	   34,   10,   13,   10,   13,   10,   32,   34,    9,   13,   10,   32,
	   34,    9,   13,   10,   32,   34,    9,   13,   10,   32,   34,    9,
	   13,   10,   32,    9,   13,   10,   32,    9,   13,   10,   13,   10,
	   95,   70,   69,   65,   84,   85,   82,   69,   95,   69,   78,   68,
	   95,   37,   32,   10,   13,   10,   13,   13,   32,   64,    9,   10,
	    9,   10,   13,   32,   64,   11,   12,   10,   32,   64,    9,   13,
	   97,  108,   99,  107,  103,  114,  111,  117,  110,  100,   58,   10,
	   10,   10,   32,   35,   37,   42,   64,   66,   67,   77,   78,   87,
	   89,    9,   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,
	   84,   10,   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,
	   78,   10,   68,   10,   95,   10,   37,   10,   32,   10,   32,   34,
	   35,   37,   42,   64,   66,   67,   77,   78,   87,   89,  124,    9,
	   13,  101,  111,  114,  112,  116,   98,   98,  101,  114,   58,   10,
	   10,   10,   32,   35,   67,  124,    9,   13,   10,  114,   10,  105,
	   10,  107,   10,  101,   10,  121,   10,   58,  105,  107,  101,  121,
	   58,   10,   10,   10,   32,   35,   37,   64,   66,   67,   77,    9,
	   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,
	   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,
	   68,   10,   95,   10,   37,   10,   97,  108,   10,   99,   10,  107,
	   10,  103,   10,  114,   10,  111,   10,  117,   10,  110,   10,  100,
	   10,   58,   10,  111,   10,  107,   10,  101,   10,  115,   10,  111,
	  114,   10,   98,   10,   98,   10,  101,   10,  114,   10,  105,   10,
	  107,   10,  101,   10,  121,   10,   97,   10,  116,   10,  101,   97,
	  116,  101,   58,   10,   10,   10,   32,   35,   37,   42,   64,   66,
	   67,   77,   78,   87,   89,    9,   13,   10,   95,   10,   70,   10,
	   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,
	   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,
	   32,   10,   97,  108,   10,   99,   10,  107,   10,  103,   10,  114,
	   10,  111,   10,  117,   10,  110,   10,  100,   10,   58,   10,  111,
	   10,  107,   10,  101,   10,  115,   10,  101,  114,   10,  112,   10,
	  116,   10,  105,   10,  107,   10,  101,   10,  121,   10,   97,   10,
	  116,   10,  101,   10,  104,   10,  101,   10,  110,   10,   97,   10,
	   32,   10,  103,  107,   10,  111,   10,  116,   10,  116,   10,   97,
	   10,  110,   10,  111,   10,  119,   10,   32,   10,  104,   10,  111,
	   10,  119,  104,  101,  110,   97,   32,  103,  107,  111,  116,  116,
	   97,  110,  111,  119,   32,  104,  111,  119,   32,  124,    9,   13,
	   10,   32,   92,  124,    9,   13,   10,   92,  124,   10,   92,   10,
	   32,   92,  124,    9,   13,   10,   32,   34,   35,   37,   42,   64,
	   66,   67,   77,   78,   87,   89,  124,    9,   13,   10,  108,   10,
	  111,   10,  107,   10,  101,   10,  115,   10,   58,   10,  101,  114,
	   10,  112,   10,  116,   10,  105,   10,  107,   10,  101,   10,  121,
	   10,   97,   10,  116,   10,  101,   10,  104,   10,  101,   10,  110,
	   10,   97,   10,   32,   10,  103,  107,   10,  111,   10,  116,   10,
	  116,   10,   97,   10,  110,   10,  111,   10,  119,   10,   32,   10,
	  104,   10,  111,   10,  119,  111,  107,  101,  115,   58,   10,   10,
	   10,   32,   35,   37,   42,   64,   67,   77,   78,   87,   89,    9,
	   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,
	   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,
	   68,   10,   95,   10,   37,   10,   32,   10,  101,  114,   10,  112,
	   10,  116,   10,  105,   10,  107,   10,  101,   10,  121,   10,   58,
	   10,   97,   10,  116,   10,  101,   10,  104,   10,  101,   10,  110,
	   10,   97,   10,   32,   10,  103,  107,   10,  111,   10,  116,   10,
	  116,   10,   97,   10,  110,   10,  111,   10,  119,   10,   32,   10,
	  104,   10,  111,   10,  119,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   15,    1,    1,   14,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,   12,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,   14,    3,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    5,    2,    2,    2,    2,    2,    2,    1,
	    1,    1,    1,    1,    1,    1,    8,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    1,
	    1,    1,    1,    1,    1,   12,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    1,    1,    1,    1,    1,    2,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    2,    4,    3,    2,    4,
	   14,    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    1,    1,
	    1,    1,    1,    1,    1,   11,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    0
	};
}

private static final byte _lexer_single_lengths[] = init__lexer_single_lengths_0();


private static byte[] init__lexer_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    0,    1,    0,    0,    0,    0,    1,    1,    1,
	    1,    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    1,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    1,    1,    0,    0,    1,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0
	};
}

private static final byte _lexer_range_lengths[] = init__lexer_range_lengths_0();


private static short[] init__lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,   17,   19,   21,   37,   39,   41,   44,   47,   52,   57,
	   62,   67,   71,   75,   78,   80,   82,   84,   86,   88,   90,   92,
	   94,   96,   98,  100,  102,  104,  106,  108,  110,  113,  116,  121,
	  128,  133,  136,  138,  140,  142,  144,  146,  148,  150,  152,  154,
	  156,  158,  172,  175,  178,  181,  184,  187,  190,  193,  196,  199,
	  202,  205,  208,  211,  214,  217,  233,  237,  239,  241,  243,  245,
	  247,  249,  251,  253,  255,  262,  265,  268,  271,  274,  277,  280,
	  282,  284,  286,  288,  290,  292,  294,  304,  307,  310,  313,  316,
	  319,  322,  325,  328,  331,  334,  337,  340,  343,  346,  350,  353,
	  356,  359,  362,  365,  368,  371,  374,  377,  380,  383,  386,  389,
	  393,  396,  399,  402,  405,  408,  411,  414,  417,  420,  423,  426,
	  428,  430,  432,  434,  436,  438,  452,  455,  458,  461,  464,  467,
	  470,  473,  476,  479,  482,  485,  488,  491,  494,  497,  501,  504,
	  507,  510,  513,  516,  519,  522,  525,  528,  531,  534,  537,  540,
	  544,  547,  550,  553,  556,  559,  562,  565,  568,  571,  574,  577,
	  580,  583,  586,  590,  593,  596,  599,  602,  605,  608,  611,  614,
	  617,  620,  623,  625,  627,  629,  631,  633,  636,  638,  640,  642,
	  644,  646,  648,  650,  652,  654,  656,  658,  662,  668,  672,  675,
	  681,  697,  700,  703,  706,  709,  712,  715,  719,  722,  725,  728,
	  731,  734,  737,  740,  743,  746,  749,  752,  755,  758,  761,  765,
	  768,  771,  774,  777,  780,  783,  786,  789,  792,  795,  798,  800,
	  802,  804,  806,  808,  810,  812,  825,  828,  831,  834,  837,  840,
	  843,  846,  849,  852,  855,  858,  861,  864,  867,  870,  874,  877,
	  880,  883,  886,  889,  892,  895,  898,  901,  904,  907,  910,  913,
	  916,  919,  923,  926,  929,  932,  935,  938,  941,  944,  947,  950,
	  953,  956
	};
}

private static final short _lexer_index_offsets[] = init__lexer_index_offsets_0();


private static short[] init__lexer_indicies_0()
{
	return new short [] {
	    1,    3,    2,    4,    5,    6,    7,    8,    9,   10,   11,    7,
	   12,   13,   14,    2,    0,   15,    0,    2,    0,    3,    2,    4,
	    5,    6,    7,    8,    9,   10,   11,    7,   12,   13,   14,    2,
	    0,   16,    0,   17,    0,   19,   20,   18,   22,   23,   21,   26,
	   25,   27,   25,   24,   30,   29,   31,   29,   28,   30,   29,   32,
	   29,   28,   30,   29,   33,   29,   28,   35,   34,   34,    0,    3,
	   36,   36,    0,   38,   39,   37,    3,    0,   40,    0,   41,    0,
	   42,    0,   43,    0,   44,    0,   45,    0,   46,    0,   47,    0,
	   48,    0,   49,    0,   50,    0,   51,    0,   52,    0,   53,    0,
	   54,    0,   56,   57,   55,   59,   60,   58,    0,    0,    0,    0,
	   61,   62,   63,   62,   62,   65,   64,   61,    3,   66,    8,   66,
	    0,   67,   68,    0,   69,    0,   70,    0,   71,    0,   72,    0,
	   73,    0,   74,    0,   75,    0,   76,    0,   77,    0,   79,   78,
	   81,   80,   81,   82,   83,   84,   85,   83,   86,   87,   88,   85,
	   89,   90,   82,   80,   81,   91,   80,   81,   92,   80,   81,   93,
	   80,   81,   94,   80,   81,   95,   80,   81,   96,   80,   81,   97,
	   80,   81,   98,   80,   81,   99,   80,   81,  100,   80,   81,  101,
	   80,   81,  102,   80,   81,  103,   80,   81,  104,   80,   81,  105,
	   80,  107,  106,  108,  109,  110,  111,  112,  113,  114,  115,  111,
	  116,  117,  118,  106,    0,  119,  120,  121,    0,  122,    0,  123,
	    0,  124,    0,  125,    0,  126,    0,  127,    0,  128,    0,  130,
	  129,  132,  131,  132,  133,  134,  135,  134,  133,  131,  132,  136,
	  131,  132,  137,  131,  132,  138,  131,  132,  139,  131,  132,  140,
	  131,  132,  141,  131,  142,    0,  143,    0,  144,    0,  145,    0,
	  146,    0,  148,  147,  150,  149,  150,  151,  152,  153,  152,  154,
	  155,  156,  151,  149,  150,  157,  149,  150,  158,  149,  150,  159,
	  149,  150,  160,  149,  150,  161,  149,  150,  162,  149,  150,  163,
	  149,  150,  164,  149,  150,  165,  149,  150,  166,  149,  150,  167,
	  149,  150,  168,  149,  150,  169,  149,  150,  170,  149,  150,  171,
	  172,  149,  150,  173,  149,  150,  174,  149,  150,  175,  149,  150,
	  176,  149,  150,  177,  149,  150,  178,  149,  150,  179,  149,  150,
	  180,  149,  150,  181,  149,  150,  182,  149,  150,  183,  149,  150,
	  184,  149,  150,  180,  149,  150,  185,  186,  149,  150,  187,  149,
	  150,  188,  149,  150,  189,  149,  150,  180,  149,  150,  190,  149,
	  150,  191,  149,  150,  192,  149,  150,  180,  149,  150,  193,  149,
	  150,  194,  149,  150,  180,  149,  195,    0,  196,    0,  197,    0,
	  198,    0,  200,  199,  202,  201,  202,  203,  204,  205,  206,  204,
	  207,  208,  209,  206,  210,  211,  203,  201,  202,  212,  201,  202,
	  213,  201,  202,  214,  201,  202,  215,  201,  202,  216,  201,  202,
	  217,  201,  202,  218,  201,  202,  219,  201,  202,  220,  201,  202,
	  221,  201,  202,  222,  201,  202,  223,  201,  202,  224,  201,  202,
	  225,  201,  202,  226,  201,  202,  227,  228,  201,  202,  229,  201,
	  202,  230,  201,  202,  231,  201,  202,  232,  201,  202,  233,  201,
	  202,  234,  201,  202,  235,  201,  202,  236,  201,  202,  226,  201,
	  202,  237,  201,  202,  238,  201,  202,  239,  201,  202,  236,  201,
	  202,  240,  241,  201,  202,  242,  201,  202,  243,  201,  202,  244,
	  201,  202,  245,  201,  202,  246,  201,  202,  236,  201,  202,  247,
	  201,  202,  248,  201,  202,  236,  201,  202,  249,  201,  202,  250,
	  201,  202,  243,  201,  202,  251,  201,  202,  252,  201,  202,  253,
	  254,  201,  202,  255,  201,  202,  256,  201,  202,  257,  201,  202,
	  243,  201,  202,  258,  201,  202,  259,  201,  202,  260,  201,  202,
	  261,  201,  202,  262,  201,  202,  263,  201,  202,  243,  201,  264,
	    0,  265,    0,  123,    0,  266,    0,  267,    0,  268,  269,    0,
	  270,    0,  271,    0,  272,    0,  123,    0,  273,    0,  274,    0,
	  275,    0,  276,    0,  277,    0,  278,    0,  123,    0,  279,  280,
	  279,    0,  283,  282,  284,  285,  282,  281,    0,  287,  288,  286,
	    0,  287,  286,  283,  289,  287,  288,  289,  286,  283,  290,  291,
	  292,  293,  294,  295,  296,  297,  298,  294,  299,  300,  301,  290,
	    0,   81,  302,   80,   81,  303,   80,   81,  304,   80,   81,  305,
	   80,   81,  306,   80,   81,  105,   80,   81,  307,  308,   80,   81,
	  309,   80,   81,  310,   80,   81,  311,   80,   81,  312,   80,   81,
	  313,   80,   81,  306,   80,   81,  314,   80,   81,  315,   80,   81,
	  306,   80,   81,  316,   80,   81,  317,   80,   81,  310,   80,   81,
	  318,   80,   81,  319,   80,   81,  320,  321,   80,   81,  322,   80,
	   81,  323,   80,   81,  324,   80,   81,  310,   80,   81,  325,   80,
	   81,  326,   80,   81,  327,   80,   81,  328,   80,   81,  329,   80,
	   81,  330,   80,   81,  310,   80,  331,    0,  332,    0,  333,    0,
	  334,    0,  335,    0,  337,  336,  339,  338,  339,  340,  341,  342,
	  343,  341,  344,  345,  343,  346,  347,  340,  338,  339,  348,  338,
	  339,  349,  338,  339,  350,  338,  339,  351,  338,  339,  352,  338,
	  339,  353,  338,  339,  354,  338,  339,  355,  338,  339,  356,  338,
	  339,  357,  338,  339,  358,  338,  339,  359,  338,  339,  360,  338,
	  339,  361,  338,  339,  362,  338,  339,  363,  364,  338,  339,  365,
	  338,  339,  366,  338,  339,  367,  338,  339,  368,  338,  339,  369,
	  338,  339,  370,  338,  339,  362,  338,  339,  371,  338,  339,  372,
	  338,  339,  370,  338,  339,  373,  338,  339,  374,  338,  339,  366,
	  338,  339,  375,  338,  339,  376,  338,  339,  377,  378,  338,  339,
	  379,  338,  339,  380,  338,  339,  381,  338,  339,  366,  338,  339,
	  382,  338,  339,  383,  338,  339,  384,  338,  339,  385,  338,  339,
	  386,  338,  339,  387,  338,  339,  366,  338,  388,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   66,  131,
	  194,  197,  211,    3,    6,    7,    8,    9,    8,    8,    9,    8,
	   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,   14,    4,
	   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,   24,   25,
	   26,   27,   28,   29,   30,  301,   32,   33,    4,   16,   33,    4,
	   16,   35,   36,    4,   35,   34,   36,   38,  250,   39,   40,   41,
	   42,   43,   44,   45,   46,   47,   48,   49,   48,   49,   49,    4,
	   50,   64,  217,  223,  230,  233,  236,   51,   52,   53,   54,   55,
	   56,   57,   58,   59,   60,   61,   62,   63,    4,   65,    4,    4,
	    5,   15,   17,   31,   34,   37,   66,  131,  194,  197,  211,   67,
	   69,   83,   68,   31,   70,   71,   72,   73,   74,   75,   76,   75,
	   76,   76,    4,   77,   78,   79,   80,   81,   82,   65,   84,   85,
	   86,   87,   88,   89,   90,   89,   90,   90,    4,   91,  105,  119,
	  128,   92,   93,   94,   95,   96,   97,   98,   99,  100,  101,  102,
	  103,  104,    4,  106,  115,  107,  108,  109,  110,  111,  112,  113,
	  114,   65,  116,  117,  118,  120,  124,  121,  122,  123,  125,  126,
	  127,  129,  130,  132,  133,  134,  135,  136,  137,  136,  137,  137,
	    4,  138,  152,  153,  167,  174,  177,  180,  139,  140,  141,  142,
	  143,  144,  145,  146,  147,  148,  149,  150,  151,    4,   65,  154,
	  163,  155,  156,  157,  158,  159,  160,  161,  162,  164,  165,  166,
	  168,  170,  169,  152,  171,  172,  173,  175,  176,  178,  179,  181,
	  182,  183,  187,  184,  185,  186,  188,  189,  190,  191,  192,  193,
	  195,  196,  198,  199,  200,  204,  201,  202,  203,  205,  206,  207,
	  208,  209,  210,  211,  212,  213,  215,  216,  214,  212,  213,  214,
	  212,  215,  216,    5,   15,   17,   31,   34,   37,   66,  131,  194,
	  197,  211,  218,  219,  220,  221,  222,  224,  226,  225,   64,  227,
	  228,  229,  231,  232,  234,  235,  237,  238,  239,  243,  240,  241,
	  242,  244,  245,  246,  247,  248,  249,  251,  252,  253,  254,  255,
	  256,  257,  256,  257,  257,    4,  258,  272,  273,  281,  284,  287,
	  259,  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,  270,
	  271,    4,   65,  274,  276,  275,  272,  277,  278,  279,  280,  282,
	  283,  285,  286,  288,  289,  290,  294,  291,  292,  293,  295,  296,
	  297,  298,  299,  300,    0
	};
}

private static final short _lexer_trans_targs[] = init__lexer_trans_targs_0();


private static short[] init__lexer_trans_actions_0()
{
	return new short [] {
	   43,    0,    0,   54,    3,    1,    0,   29,    1,   29,   29,   29,
	   29,   29,   35,    0,    0,    0,    7,  139,   48,    0,  102,    9,
	    5,   45,  134,   45,    0,   33,  122,   33,   33,    0,   11,  106,
	    0,    0,  114,   25,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,   57,  149,  126,    0,  110,
	   23,    0,   27,  118,   27,   51,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   72,
	   33,   84,   84,   84,   84,   84,   84,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   15,   15,   31,  130,
	   60,   57,   31,   63,   57,   63,   63,   63,   63,   63,   66,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,
	   54,    0,   81,   84,    0,    0,    0,    0,    0,   21,    0,    0,
	    0,    0,    0,   57,  144,    0,   54,    0,   69,   33,   84,   84,
	   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   13,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   13,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,
	   75,   33,   84,   84,   84,   84,   84,   84,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   17,   17,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   37,   37,   54,   37,   87,    0,    0,
	   39,    0,    0,   93,   90,   41,   96,   90,   96,   96,   96,   96,
	   96,   99,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	   57,  144,    0,   54,    0,   78,   33,   84,   84,   84,   84,   84,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   19,   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0
	};
}

private static final short _lexer_trans_actions[] = init__lexer_trans_actions_0();


private static short[] init__lexer_eof_actions_0()
{
	return new short [] {
	    0,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 301;

static final int lexer_en_main = 1;


// line 159 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"

  public void scan(String source)  {
    String input = source + "\n%_FEATURE_END_%";
    byte[] data = null;
    try {
      data = input.getBytes("UTF-8");
    } catch(UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    int cs, p = 0, pe = data.length;
    int eof = pe;

    int lineNumber = 1;
    int lastNewline = 0;

    int contentStart = -1;
    int currentLine = -1;
    int docstringContentTypeStart = -1;
    int docstringContentTypeEnd = -1;
    int startCol = -1;
    int nextKeywordStart = -1;
    int keywordStart = -1;
    String keyword = null;
    List<String> currentRow = null;

    
// line 496 "java/src/main/java/gherkin/lexer/En_au.java"
	{
	cs = lexer_start;
	}

// line 185 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
    
// line 503 "java/src/main/java/gherkin/lexer/En_au.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _lexer_key_offsets[cs];
	_trans = _lexer_index_offsets[cs];
	_klen = _lexer_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _lexer_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _lexer_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _lexer_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _lexer_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _lexer_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _lexer_indicies[_trans];
	cs = _lexer_trans_targs[_trans];

	if ( _lexer_trans_actions[_trans] != 0 ) {
		_acts = _lexer_trans_actions[_trans];
		_nacts = (int) _lexer_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _lexer_actions[_acts++] )
			{
	case 0:
// line 16 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replaceAll("\\\\\"\\\\\"\\\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      String con = substring(data, contentStart, p).trim();
      currentRow.add(con
        .replaceAll("\\\\\\|", "|")
        .replaceAll("\\\\n", "\n")
        .replaceAll("\\\\\\\\", "\\\\")
      );
    }
	break;
	case 22:
// line 136 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 764 "java/src/main/java/gherkin/lexer/En_au.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	int __acts = _lexer_eof_actions[cs];
	int __nacts = (int) _lexer_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _lexer_actions[__acts++] ) {
	case 23:
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 796 "java/src/main/java/gherkin/lexer/En_au.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_au.java.rl"
  }

  private String keywordContent(byte[] data, int p, int eof, int nextKeywordStart, int contentStart) {
    int endPoint = (nextKeywordStart == -1 || (p == eof)) ? p : nextKeywordStart;
    return substring(data, contentStart, endPoint);
  }

  private String[] nameAndUnindentedDescription(int startCol, String text) {
    String[] lines = text.split("\n");
    String name = lines.length > 0 ? lines[0].trim() : "";
    StringBuffer description = new StringBuffer();
    for(int i = 1; i < lines.length; i++) {
      description.append(lines[i]);
      description.append("\n");
    }
    return new String[]{name, unindent(startCol+2, description.toString()).replaceAll("\\s+$", "")};
  }

  private String unindent(int startCol, String text) {
    return Pattern.compile("^[\t ]{0," + startCol + "}", Pattern.MULTILINE).matcher(text).replaceAll("");
  }

  private String currentLineContent(byte[] data, int lastNewline) {
    return substring(data, lastNewline, data.length).trim();
  }

  private String substring(byte[] data, int start, int end) {
    try {
      return new String(data, start, end-start, "utf-8");
    } catch(java.io.UnsupportedEncodingException e) {
      throw new RuntimeException("Internal error", e);
    }
  }
}
