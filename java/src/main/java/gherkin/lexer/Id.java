
// line 1 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class Id implements Lexer {
  
// line 150 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"


  private final Listener listener;

  public Id(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/Id.java"
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
	    0,    0,   18,   19,   20,   37,   38,   39,   41,   43,   48,   53,
	   58,   63,   67,   71,   73,   74,   75,   76,   77,   78,   79,   80,
	   81,   82,   83,   84,   85,   86,   87,   88,   89,   91,   93,   98,
	  105,  110,  111,  112,  113,  114,  115,  116,  117,  118,  125,  127,
	  129,  131,  133,  135,  152,  154,  156,  157,  158,  159,  160,  161,
	  175,  177,  179,  181,  183,  185,  187,  189,  191,  193,  195,  197,
	  199,  201,  203,  205,  208,  210,  212,  214,  216,  218,  220,  222,
	  224,  226,  228,  230,  232,  234,  236,  238,  240,  242,  244,  246,
	  248,  250,  252,  255,  257,  259,  261,  263,  265,  267,  269,  271,
	  273,  274,  275,  276,  277,  278,  279,  280,  281,  282,  283,  284,
	  295,  297,  299,  301,  303,  305,  307,  309,  311,  313,  315,  317,
	  319,  321,  323,  325,  327,  329,  331,  333,  335,  337,  339,  341,
	  343,  345,  347,  349,  351,  353,  355,  357,  359,  361,  363,  366,
	  368,  370,  372,  374,  376,  378,  379,  380,  381,  382,  383,  384,
	  385,  386,  387,  388,  389,  390,  391,  393,  394,  395,  396,  397,
	  398,  399,  400,  401,  402,  416,  418,  420,  422,  424,  426,  428,
	  430,  432,  434,  436,  438,  440,  442,  444,  446,  449,  451,  453,
	  455,  457,  459,  461,  463,  465,  467,  469,  471,  473,  475,  477,
	  479,  481,  483,  485,  487,  489,  491,  493,  495,  497,  499,  500,
	  501,  515,  517,  519,  521,  523,  525,  527,  529,  531,  533,  535,
	  537,  539,  541,  543,  545,  548,  551,  553,  555,  557,  559,  561,
	  563,  565,  567,  569,  571,  573,  575,  577,  579,  581,  583,  585,
	  587,  589,  591,  593,  595,  597,  600,  602,  604,  606,  608,  610,
	  612,  614,  616,  618,  619,  620,  621,  625,  631,  634,  636,  642,
	  659
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   67,   68,   70,   75,
	   77,   83,   84,  124,    9,   13,  -69,  -65,   10,   32,   34,   35,
	   37,   42,   64,   67,   68,   70,   75,   77,   83,   84,  124,    9,
	   13,   34,   34,   10,   13,   10,   13,   10,   32,   34,    9,   13,
	   10,   32,   34,    9,   13,   10,   32,   34,    9,   13,   10,   32,
	   34,    9,   13,   10,   32,    9,   13,   10,   32,    9,   13,   10,
	   13,   10,   95,   70,   69,   65,   84,   85,   82,   69,   95,   69,
	   78,   68,   95,   37,   32,   10,   13,   10,   13,   13,   32,   64,
	    9,   10,    9,   10,   13,   32,   64,   11,   12,   10,   32,   64,
	    9,   13,  111,  110,  116,  111,  104,   58,   10,   10,   10,   32,
	   35,   70,  124,    9,   13,   10,  105,   10,  116,   10,  117,   10,
	  114,   10,   58,   10,   32,   34,   35,   37,   42,   64,   67,   68,
	   70,   75,   77,   83,   84,  124,    9,   13,   97,  101,  110,  115,
	   97,  114,   58,   10,   10,   10,   32,   35,   37,   42,   64,   68,
	   70,   75,   77,   83,   84,    9,   13,   10,   95,   10,   70,   10,
	   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,
	   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,
	   32,   10,   97,  101,   10,  110,   10,  110,   10,  103,   10,   97,
	   10,  105,   10,  116,   10,  117,   10,  114,   10,   58,   10,  101,
	   10,  116,   10,  105,   10,  107,   10,   97,   10,   97,   10,  107,
	   10,  101,   10,  110,   10,   97,   10,  114,   10,  105,   10,  111,
	   10,   32,   58,   10,  107,   10,  111,   10,  110,   10,  115,   10,
	  101,   10,  112,   10,   97,   10,  112,   10,  105,  110,  103,   97,
	  110,  105,  116,  117,  114,   58,   10,   10,   10,   32,   35,   37,
	   64,   67,   68,   70,   83,    9,   13,   10,   95,   10,   70,   10,
	   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,
	   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,
	  111,   10,  110,   10,  116,   10,  111,   10,  104,   10,   58,   10,
	   97,   10,  115,   10,   97,   10,  114,   10,  105,   10,  116,   10,
	  117,   10,  107,   10,  101,   10,  110,   10,   97,   10,  114,   10,
	  105,   10,  111,   10,   32,   58,   10,  107,   10,  111,   10,  110,
	   10,  115,   10,  101,   10,  112,  101,  116,  105,  107,   97,   97,
	  107,  101,  110,   97,  114,  105,  111,   32,   58,  107,  111,  110,
	  115,  101,  112,   58,   10,   10,   10,   32,   35,   37,   42,   64,
	   68,   70,   75,   77,   83,   84,    9,   13,   10,   95,   10,   70,
	   10,   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,
	   10,   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,
	   10,   32,   10,   97,  101,   10,  110,   10,  110,   10,  103,   10,
	   97,   10,  105,   10,  116,   10,  117,   10,  114,   10,   58,   10,
	  101,   10,  116,   10,  105,   10,  107,   10,   97,   10,   97,   10,
	  107,   10,  101,   10,  110,   10,   97,   10,  114,   10,  105,   10,
	  111,   10,   97,   10,  112,   10,  105,   10,   10,   10,   32,   35,
	   37,   42,   64,   68,   70,   75,   77,   83,   84,    9,   13,   10,
	   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,   85,   10,
	   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,   68,   10,
	   95,   10,   37,   10,   32,   10,   97,  101,   10,  110,  115,   10,
	   97,   10,  114,   10,   58,   10,  110,   10,  103,   10,   97,   10,
	  110,   10,  105,   10,  116,   10,  117,   10,  101,   10,  116,   10,
	  105,   10,  107,   10,   97,   10,   97,   10,  107,   10,  101,   10,
	  110,   10,   97,   10,  114,   10,  105,   10,  111,   10,   32,   58,
	   10,  107,   10,  111,   10,  110,   10,  115,   10,  101,   10,  112,
	   10,   97,   10,  112,   10,  105,   97,  112,  105,   32,  124,    9,
	   13,   10,   32,   92,  124,    9,   13,   10,   92,  124,   10,   92,
	   10,   32,   92,  124,    9,   13,   10,   32,   34,   35,   37,   42,
	   64,   67,   68,   70,   75,   77,   83,   84,  124,    9,   13,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   16,    1,    1,   15,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    1,    1,    1,    1,    1,    1,    1,    1,    5,    2,    2,
	    2,    2,    2,   15,    2,    2,    1,    1,    1,    1,    1,   12,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    9,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,
	    2,    2,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    2,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,   12,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    1,    1,
	   12,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    3,    3,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    1,    1,    1,    2,    4,    3,    2,    4,   15,
	    0
	};
}

private static final byte _lexer_single_lengths[] = init__lexer_single_lengths_0();


private static byte[] init__lexer_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    0,    1,    0,    0,    0,    0,    1,    1,    1,
	    1,    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    1,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
	    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    1,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    1,    1,    0,    0,    1,    1,
	    0
	};
}

private static final byte _lexer_range_lengths[] = init__lexer_range_lengths_0();


private static short[] init__lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,   18,   20,   22,   39,   41,   43,   46,   49,   54,   59,
	   64,   69,   73,   77,   80,   82,   84,   86,   88,   90,   92,   94,
	   96,   98,  100,  102,  104,  106,  108,  110,  112,  115,  118,  123,
	  130,  135,  137,  139,  141,  143,  145,  147,  149,  151,  158,  161,
	  164,  167,  170,  173,  190,  193,  196,  198,  200,  202,  204,  206,
	  220,  223,  226,  229,  232,  235,  238,  241,  244,  247,  250,  253,
	  256,  259,  262,  265,  269,  272,  275,  278,  281,  284,  287,  290,
	  293,  296,  299,  302,  305,  308,  311,  314,  317,  320,  323,  326,
	  329,  332,  335,  339,  342,  345,  348,  351,  354,  357,  360,  363,
	  366,  368,  370,  372,  374,  376,  378,  380,  382,  384,  386,  388,
	  399,  402,  405,  408,  411,  414,  417,  420,  423,  426,  429,  432,
	  435,  438,  441,  444,  447,  450,  453,  456,  459,  462,  465,  468,
	  471,  474,  477,  480,  483,  486,  489,  492,  495,  498,  501,  505,
	  508,  511,  514,  517,  520,  523,  525,  527,  529,  531,  533,  535,
	  537,  539,  541,  543,  545,  547,  549,  552,  554,  556,  558,  560,
	  562,  564,  566,  568,  570,  584,  587,  590,  593,  596,  599,  602,
	  605,  608,  611,  614,  617,  620,  623,  626,  629,  633,  636,  639,
	  642,  645,  648,  651,  654,  657,  660,  663,  666,  669,  672,  675,
	  678,  681,  684,  687,  690,  693,  696,  699,  702,  705,  708,  710,
	  712,  726,  729,  732,  735,  738,  741,  744,  747,  750,  753,  756,
	  759,  762,  765,  768,  771,  775,  779,  782,  785,  788,  791,  794,
	  797,  800,  803,  806,  809,  812,  815,  818,  821,  824,  827,  830,
	  833,  836,  839,  842,  845,  848,  852,  855,  858,  861,  864,  867,
	  870,  873,  876,  879,  881,  883,  885,  889,  895,  899,  902,  908,
	  925
	};
}

private static final short _lexer_index_offsets[] = init__lexer_index_offsets_0();


private static short[] init__lexer_indicies_0()
{
	return new short [] {
	    1,    3,    2,    4,    5,    6,    7,    8,    9,   10,   11,   12,
	   13,   14,   15,   16,    2,    0,   17,    0,    2,    0,    3,    2,
	    4,    5,    6,    7,    8,    9,   10,   11,   12,   13,   14,   15,
	   16,    2,    0,   18,    0,   19,    0,   21,   22,   20,   24,   25,
	   23,   28,   27,   29,   27,   26,   32,   31,   33,   31,   30,   32,
	   31,   34,   31,   30,   32,   31,   35,   31,   30,   37,   36,   36,
	    0,    3,   38,   38,    0,   40,   41,   39,    3,    0,   42,    0,
	   43,    0,   44,    0,   45,    0,   46,    0,   47,    0,   48,    0,
	   49,    0,   50,    0,   51,    0,   52,    0,   53,    0,   54,    0,
	   55,    0,   56,    0,   58,   59,   57,   61,   62,   60,    0,    0,
	    0,    0,   63,   64,   65,   64,   64,   67,   66,   63,    3,   68,
	    8,   68,    0,   69,    0,   70,    0,   71,    0,   72,    0,   73,
	    0,   74,    0,   76,   75,   78,   77,   78,   79,   80,   81,   80,
	   79,   77,   78,   82,   77,   78,   83,   77,   78,   84,   77,   78,
	   85,   77,   78,   86,   77,   88,   87,   89,   90,   91,   92,   93,
	   94,   95,   96,   97,   98,   99,  100,  101,   87,    0,  102,  103,
	    0,  104,  105,    0,  106,    0,  107,    0,  108,    0,  110,  109,
	  112,  111,  112,  113,  114,  115,  116,  114,  117,  118,  119,  120,
	  121,  122,  113,  111,  112,  123,  111,  112,  124,  111,  112,  125,
	  111,  112,  126,  111,  112,  127,  111,  112,  128,  111,  112,  129,
	  111,  112,  130,  111,  112,  131,  111,  112,  132,  111,  112,  133,
	  111,  112,  134,  111,  112,  135,  111,  112,  136,  111,  112,  137,
	  111,  112,  138,  139,  111,  112,  140,  111,  112,  141,  111,  112,
	  142,  111,  112,  138,  111,  112,  143,  111,  112,  144,  111,  112,
	  145,  111,  112,  146,  111,  112,  137,  111,  112,  147,  111,  112,
	  148,  111,  112,  149,  111,  112,  150,  111,  112,  140,  111,  112,
	  149,  111,  112,  151,  111,  112,  152,  111,  112,  153,  111,  112,
	  154,  111,  112,  155,  111,  112,  156,  111,  112,  157,  111,  112,
	  158,  137,  111,  112,  159,  111,  112,  160,  111,  112,  161,  111,
	  112,  162,  111,  112,  163,  111,  112,  146,  111,  112,  164,  111,
	  112,  165,  111,  112,  140,  111,  166,    0,  167,    0,  168,    0,
	  104,    0,  169,    0,  170,    0,  171,    0,  172,    0,  173,    0,
	  175,  174,  177,  176,  177,  178,  179,  180,  179,  181,  182,  183,
	  184,  178,  176,  177,  185,  176,  177,  186,  176,  177,  187,  176,
	  177,  188,  176,  177,  189,  176,  177,  190,  176,  177,  191,  176,
	  177,  192,  176,  177,  193,  176,  177,  194,  176,  177,  195,  176,
	  177,  196,  176,  177,  197,  176,  177,  198,  176,  177,  199,  176,
	  177,  200,  176,  177,  201,  176,  177,  202,  176,  177,  203,  176,
	  177,  204,  176,  177,  205,  176,  177,  206,  176,  177,  207,  176,
	  177,  203,  176,  177,  208,  176,  177,  209,  176,  177,  207,  176,
	  177,  210,  176,  177,  211,  176,  177,  212,  176,  177,  213,  176,
	  177,  214,  176,  177,  215,  176,  177,  216,  176,  177,  217,  204,
	  176,  177,  218,  176,  177,  219,  176,  177,  220,  176,  177,  221,
	  176,  177,  222,  176,  177,  203,  176,  223,    0,  224,    0,  225,
	    0,  226,    0,  104,    0,  225,    0,  227,    0,  228,    0,  229,
	    0,  230,    0,  231,    0,  232,    0,  233,    0,  234,  235,    0,
	  236,    0,  237,    0,  238,    0,  239,    0,  240,    0,  241,    0,
	  242,    0,  244,  243,  246,  245,  246,  247,  248,  249,  250,  248,
	  251,  252,  253,  254,  255,  256,  247,  245,  246,  257,  245,  246,
	  258,  245,  246,  259,  245,  246,  260,  245,  246,  261,  245,  246,
	  262,  245,  246,  263,  245,  246,  264,  245,  246,  265,  245,  246,
	  266,  245,  246,  267,  245,  246,  268,  245,  246,  269,  245,  246,
	  270,  245,  246,  271,  245,  246,  272,  273,  245,  246,  274,  245,
	  246,  275,  245,  246,  276,  245,  246,  272,  245,  246,  277,  245,
	  246,  278,  245,  246,  279,  245,  246,  280,  245,  246,  271,  245,
	  246,  281,  245,  246,  282,  245,  246,  283,  245,  246,  284,  245,
	  246,  274,  245,  246,  283,  245,  246,  285,  245,  246,  286,  245,
	  246,  287,  245,  246,  288,  245,  246,  289,  245,  246,  290,  245,
	  246,  280,  245,  246,  291,  245,  246,  292,  245,  246,  274,  245,
	  294,  293,  296,  295,  296,  297,  298,  299,  300,  298,  301,  302,
	  303,  304,  305,  306,  297,  295,  296,  307,  295,  296,  308,  295,
	  296,  309,  295,  296,  310,  295,  296,  311,  295,  296,  312,  295,
	  296,  313,  295,  296,  314,  295,  296,  315,  295,  296,  316,  295,
	  296,  317,  295,  296,  318,  295,  296,  319,  295,  296,  320,  295,
	  296,  321,  295,  296,  322,  323,  295,  296,  324,  325,  295,  296,
	  326,  295,  296,  327,  295,  296,  321,  295,  296,  328,  295,  296,
	  329,  295,  296,  330,  295,  296,  324,  295,  296,  331,  295,  296,
	  332,  295,  296,  326,  295,  296,  333,  295,  296,  334,  295,  296,
	  335,  295,  296,  336,  295,  296,  324,  295,  296,  335,  295,  296,
	  337,  295,  296,  338,  295,  296,  339,  295,  296,  340,  295,  296,
	  341,  295,  296,  342,  295,  296,  343,  295,  296,  344,  321,  295,
	  296,  345,  295,  296,  346,  295,  296,  347,  295,  296,  348,  295,
	  296,  349,  295,  296,  327,  295,  296,  350,  295,  296,  351,  295,
	  296,  324,  295,  352,    0,  353,    0,  104,    0,  354,  355,  354,
	    0,  358,  357,  359,  360,  357,  356,    0,  362,  363,  361,    0,
	  362,  361,  358,  364,  362,  363,  364,  361,  358,  365,  366,  367,
	  368,  369,  370,  371,  372,  373,  374,  375,  376,  377,  378,  365,
	    0,  379,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   52,  112,
	  161,  166,  167,  279,  282,    3,    6,    7,    8,    9,    8,    8,
	    9,    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,
	   14,    4,   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,
	   24,   25,   26,   27,   28,   29,   30,  288,   32,   33,    4,   16,
	   33,    4,   16,   35,   36,    4,   35,   34,   36,   38,   39,   40,
	   41,   42,   43,   44,   45,   44,   45,   45,    4,   46,   47,   48,
	   49,   50,   51,    4,    4,    5,   15,   17,   31,   34,   37,   52,
	  112,  161,  166,  167,  279,  282,   53,  108,   31,   54,   55,   56,
	   57,   58,   59,   58,   59,   59,    4,   60,   74,   75,   80,   85,
	   90,   91,  105,   61,   62,   63,   64,   65,   66,   67,   68,   69,
	   70,   71,   72,   73,    4,   51,   76,   77,   74,   78,   79,   81,
	   82,   83,   84,   86,   87,   88,   89,   92,   93,   94,   95,   96,
	   97,   98,   99,  100,  101,  102,  103,  104,  106,  107,  109,  110,
	  111,  113,  114,  115,  116,  117,  118,  119,  118,  119,  119,    4,
	  120,  134,  140,  144,  147,  121,  122,  123,  124,  125,  126,  127,
	  128,  129,  130,  131,  132,  133,    4,  135,  136,  137,  138,  139,
	   51,  141,  142,  143,  145,  146,  148,  149,  150,  151,  152,  153,
	  154,  155,  156,  157,  158,  159,  160,  162,  163,  164,  165,  168,
	  169,  170,  171,  172,  173,  174,  175,  226,  176,  177,  178,  179,
	  180,  181,  182,  183,  184,  183,  184,  184,    4,  185,  199,  200,
	  205,  210,  215,  216,  223,  186,  187,  188,  189,  190,  191,  192,
	  193,  194,  195,  196,  197,  198,    4,   51,  201,  202,  199,  203,
	  204,  206,  207,  208,  209,  211,  212,  213,  214,  217,  218,  219,
	  220,  221,  222,  224,  225,  227,  228,  227,  228,  228,    4,  229,
	  243,  244,  253,  256,  261,  262,  276,  230,  231,  232,  233,  234,
	  235,  236,  237,  238,  239,  240,  241,  242,    4,   51,  245,  249,
	  243,  246,  247,  248,  250,  251,  252,  254,  255,  257,  258,  259,
	  260,  263,  264,  265,  266,  267,  268,  269,  270,  271,  272,  273,
	  274,  275,  277,  278,  280,  281,  282,  283,  284,  286,  287,  285,
	  283,  284,  285,  283,  286,  287,    5,   15,   17,   31,   34,   37,
	   52,  112,  161,  166,  167,  279,  282,    0
	};
}

private static final short _lexer_trans_targs[] = init__lexer_trans_targs_0();


private static short[] init__lexer_trans_actions_0()
{
	return new short [] {
	   43,    0,    0,   54,    3,    1,    0,   29,    1,   29,   29,   29,
	   29,   29,   29,   29,   35,    0,    0,    0,    7,  139,   48,    0,
	  102,    9,    5,   45,  134,   45,    0,   33,  122,   33,   33,    0,
	   11,  106,    0,    0,  114,   25,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,  149,  126,
	    0,  110,   23,    0,   27,  118,   27,   51,    0,    0,    0,    0,
	    0,    0,    0,   57,  144,    0,   54,    0,   81,   84,    0,    0,
	    0,    0,   21,   31,  130,   60,   57,   31,   63,   57,   63,   63,
	   63,   63,   63,   63,   63,   66,    0,    0,    0,    0,    0,    0,
	    0,   57,  144,    0,   54,    0,   72,   33,   84,   84,   84,   84,
	   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,   15,   15,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   69,
	   33,   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   13,    0,    0,    0,    0,    0,
	   13,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,   57,  144,    0,   54,    0,   78,   33,   84,   84,
	   84,   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   19,   19,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   75,   33,
	   84,   84,   84,   84,   84,   84,   84,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   17,   17,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   37,   37,   54,   37,
	   87,    0,    0,   39,    0,    0,   93,   90,   41,   96,   90,   96,
	   96,   96,   96,   96,   96,   96,   99,    0
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
	   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 288;

static final int lexer_en_main = 1;


// line 159 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"

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

    
// line 485 "java/src/main/java/gherkin/lexer/Id.java"
	{
	cs = lexer_start;
	}

// line 185 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
    
// line 492 "java/src/main/java/gherkin/lexer/Id.java"
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
// line 16 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replace("\\\"\\\"\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      String con = substring(data, contentStart, p).trim();
      currentRow.add(con
        .replace("\\|", "|")
        .replace("\\n", "\n")
        .replace("\\\\", "\\")
      );
    }
	break;
	case 22:
// line 136 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 753 "java/src/main/java/gherkin/lexer/Id.java"
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
// line 140 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 785 "java/src/main/java/gherkin/lexer/Id.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/id.java.rl"
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
