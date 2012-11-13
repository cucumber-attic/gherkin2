
// line 1 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class Cy_gb implements Lexer {
  
// line 150 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"


  private final Listener listener;

  public Cy_gb(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/Cy_gb.java"
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
	  105,  110,  113,  114,  115,  116,  117,  118,  119,  120,  121,  122,
	  123,  124,  125,  126,  127,  128,  129,  130,  141,  143,  145,  147,
	  149,  151,  153,  155,  157,  159,  161,  163,  165,  167,  169,  171,
	  173,  175,  177,  179,  181,  198,  199,  200,  201,  202,  203,  204,
	  205,  206,  207,  220,  222,  224,  226,  228,  230,  232,  234,  236,
	  238,  240,  242,  244,  246,  248,  250,  254,  256,  258,  260,  262,
	  264,  266,  268,  270,  272,  274,  276,  278,  280,  282,  284,  286,
	  288,  290,  292,  294,  296,  298,  300,  302,  304,  306,  309,  311,
	  313,  315,  317,  319,  321,  323,  325,  327,  329,  331,  332,  333,
	  334,  335,  336,  337,  338,  339,  340,  341,  342,  343,  344,  345,
	  346,  353,  355,  357,  359,  361,  363,  365,  366,  367,  368,  369,
	  370,  371,  372,  373,  374,  375,  376,  378,  379,  380,  381,  382,
	  383,  384,  385,  386,  387,  388,  389,  390,  391,  404,  406,  408,
	  410,  412,  414,  416,  418,  420,  422,  424,  426,  428,  430,  432,
	  434,  438,  440,  442,  444,  446,  448,  450,  452,  454,  456,  458,
	  460,  462,  464,  466,  468,  470,  472,  474,  476,  478,  480,  482,
	  484,  486,  488,  490,  492,  493,  494,  508,  510,  512,  514,  516,
	  518,  520,  522,  524,  526,  528,  530,  532,  534,  536,  538,  542,
	  544,  546,  548,  550,  552,  554,  556,  558,  560,  562,  564,  566,
	  568,  570,  572,  574,  576,  578,  580,  582,  584,  586,  588,  590,
	  592,  594,  596,  598,  600,  602,  604,  606,  609,  611,  613,  615,
	  617,  619,  621,  623,  625,  627,  629,  631,  632,  636,  642,  645,
	  647,  653,  670,  672,  674,  676,  678,  680,  682,  684,  686,  688,
	  690,  692,  694,  696,  698,  700,  702,  704,  706,  708,  710,  712,
	  714,  716,  718,  720,  723,  725,  727,  729,  731,  733,  735,  737,
	  739,  741,  743
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   65,   67,   69,   79,
	   80,   83,   89,  124,    9,   13,  -69,  -65,   10,   32,   34,   35,
	   37,   42,   64,   65,   67,   69,   79,   80,   83,   89,  124,    9,
	   13,   34,   34,   10,   13,   10,   13,   10,   32,   34,    9,   13,
	   10,   32,   34,    9,   13,   10,   32,   34,    9,   13,   10,   32,
	   34,    9,   13,   10,   32,    9,   13,   10,   32,    9,   13,   10,
	   13,   10,   95,   70,   69,   65,   84,   85,   82,   69,   95,   69,
	   78,   68,   95,   37,   32,   10,   13,   10,   13,   13,   32,   64,
	    9,   10,    9,   10,   13,   32,   64,   11,   12,   10,   32,   64,
	    9,   13,   32,  110,  114,  114,  104,  101,  103,  101,  100,  105,
	  103,   32,   97,  119,  101,  100,  100,   58,   10,   10,   10,   32,
	   35,   37,   64,   65,   67,   69,   83,    9,   13,   10,   95,   10,
	   70,   10,   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,
	   69,   10,   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,
	   37,   10,  114,   10,  119,   10,  101,   10,  100,   10,  100,   10,
	   58,   10,   32,   34,   35,   37,   42,   64,   65,   67,   69,   79,
	   80,   83,   89,  124,    9,   13,  101,  102,  110,  100,  105,  114,
	   58,   10,   10,   10,   32,   35,   37,   42,   64,   65,   79,   80,
	   83,   89,    9,   13,   10,   95,   10,   70,   10,   69,   10,   65,
	   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,   10,   69,
	   10,   78,   10,   68,   10,   95,   10,   37,   10,   32,   10,   32,
	  110,  114,   10,  114,   10,  104,   10,  101,   10,  103,   10,  101,
	   10,  100,   10,  105,   10,  103,   10,   32,   10,   97,   10,  119,
	   10,  101,   10,  100,   10,  100,   10,   58,   10,  110,   10,  100,
	   10,  114,   10,  121,   10,   99,   10,  101,   10,  110,   10,   97,
	   10,  114,   10,  105,   10,  111,   10,   32,   58,   10,   65,   10,
	  109,   10,  108,   10,  105,   10,  110,   10,  101,   10,  108,   10,
	  108,   10,  111,   10,  108,   10,  110,  110,  103,  104,  114,  101,
	  105,  102,  102,  116,  105,   97,  117,   58,   10,   10,   10,   32,
	   35,   65,  124,    9,   13,   10,  114,   10,  119,   10,  101,   10,
	  100,   10,  100,   10,   58,  110,  100,  114,  121,   99,  101,  110,
	   97,  114,  105,  111,   32,   58,   65,  109,  108,  105,  110,  101,
	  108,  108,  111,  108,   58,   10,   10,   10,   32,   35,   37,   42,
	   64,   65,   79,   80,   83,   89,    9,   13,   10,   95,   10,   70,
	   10,   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,
	   10,   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,
	   10,   32,   10,   32,  110,  114,   10,  114,   10,  104,   10,  101,
	   10,  103,   10,  101,   10,  100,   10,  105,   10,  103,   10,   32,
	   10,   97,   10,  119,   10,  101,   10,  100,   10,  100,   10,   58,
	   10,  110,   10,  100,   10,  114,   10,  121,   10,   99,   10,  101,
	   10,  110,   10,   97,   10,  114,   10,  105,   10,  111,   10,  110,
	   10,   10,   10,   32,   35,   37,   42,   64,   65,   67,   79,   80,
	   83,   89,    9,   13,   10,   95,   10,   70,   10,   69,   10,   65,
	   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,   10,   69,
	   10,   78,   10,   68,   10,   95,   10,   37,   10,   32,   10,   32,
	  110,  114,   10,  114,   10,  104,   10,  101,   10,  103,   10,  101,
	   10,  100,   10,  105,   10,  103,   10,   32,   10,   97,   10,  119,
	   10,  101,   10,  100,   10,  100,   10,   58,   10,  101,   10,  102,
	   10,  110,   10,  100,   10,  105,   10,  114,   10,  110,   10,  100,
	   10,  114,   10,  121,   10,   99,   10,  101,   10,  110,   10,   97,
	   10,  114,   10,  105,   10,  111,   10,   32,   58,   10,   65,   10,
	  109,   10,  108,   10,  105,   10,  110,   10,  101,   10,  108,   10,
	  108,   10,  111,   10,  108,   10,  110,  110,   32,  124,    9,   13,
	   10,   32,   92,  124,    9,   13,   10,   92,  124,   10,   92,   10,
	   32,   92,  124,    9,   13,   10,   32,   34,   35,   37,   42,   64,
	   65,   67,   69,   79,   80,   83,   89,  124,    9,   13,   10,  101,
	   10,  102,   10,  110,   10,  100,   10,  105,   10,  114,   10,  110,
	   10,  103,   10,  104,   10,  114,   10,  101,   10,  105,   10,  102,
	   10,  102,   10,  116,   10,  105,   10,   97,   10,  117,   10,   99,
	   10,  101,   10,  110,   10,   97,   10,  114,   10,  105,   10,  111,
	   10,   32,   58,   10,   65,   10,  109,   10,  108,   10,  105,   10,
	  110,   10,  101,   10,  108,   10,  108,   10,  111,   10,  108,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   16,    1,    1,   15,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    3,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    9,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,   15,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,   11,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    4,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    5,    2,    2,    2,    2,    2,    2,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    2,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,   11,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    4,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    1,    1,   12,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    4,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    1,    2,    4,    3,    2,
	    4,   15,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    0
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
	    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    1,    0,    0,
	    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0
	};
}

private static final byte _lexer_range_lengths[] = init__lexer_range_lengths_0();


private static short[] init__lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,   18,   20,   22,   39,   41,   43,   46,   49,   54,   59,
	   64,   69,   73,   77,   80,   82,   84,   86,   88,   90,   92,   94,
	   96,   98,  100,  102,  104,  106,  108,  110,  112,  115,  118,  123,
	  130,  135,  139,  141,  143,  145,  147,  149,  151,  153,  155,  157,
	  159,  161,  163,  165,  167,  169,  171,  173,  184,  187,  190,  193,
	  196,  199,  202,  205,  208,  211,  214,  217,  220,  223,  226,  229,
	  232,  235,  238,  241,  244,  261,  263,  265,  267,  269,  271,  273,
	  275,  277,  279,  292,  295,  298,  301,  304,  307,  310,  313,  316,
	  319,  322,  325,  328,  331,  334,  337,  342,  345,  348,  351,  354,
	  357,  360,  363,  366,  369,  372,  375,  378,  381,  384,  387,  390,
	  393,  396,  399,  402,  405,  408,  411,  414,  417,  420,  424,  427,
	  430,  433,  436,  439,  442,  445,  448,  451,  454,  457,  459,  461,
	  463,  465,  467,  469,  471,  473,  475,  477,  479,  481,  483,  485,
	  487,  494,  497,  500,  503,  506,  509,  512,  514,  516,  518,  520,
	  522,  524,  526,  528,  530,  532,  534,  537,  539,  541,  543,  545,
	  547,  549,  551,  553,  555,  557,  559,  561,  563,  576,  579,  582,
	  585,  588,  591,  594,  597,  600,  603,  606,  609,  612,  615,  618,
	  621,  626,  629,  632,  635,  638,  641,  644,  647,  650,  653,  656,
	  659,  662,  665,  668,  671,  674,  677,  680,  683,  686,  689,  692,
	  695,  698,  701,  704,  707,  709,  711,  725,  728,  731,  734,  737,
	  740,  743,  746,  749,  752,  755,  758,  761,  764,  767,  770,  775,
	  778,  781,  784,  787,  790,  793,  796,  799,  802,  805,  808,  811,
	  814,  817,  820,  823,  826,  829,  832,  835,  838,  841,  844,  847,
	  850,  853,  856,  859,  862,  865,  868,  871,  875,  878,  881,  884,
	  887,  890,  893,  896,  899,  902,  905,  908,  910,  914,  920,  924,
	  927,  933,  950,  953,  956,  959,  962,  965,  968,  971,  974,  977,
	  980,  983,  986,  989,  992,  995,  998, 1001, 1004, 1007, 1010, 1013,
	 1016, 1019, 1022, 1025, 1029, 1032, 1035, 1038, 1041, 1044, 1047, 1050,
	 1053, 1056, 1059
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
	    8,   68,    0,   56,   69,   70,    0,   71,    0,   72,    0,   73,
	    0,   74,    0,   75,    0,   76,    0,   77,    0,   78,    0,   79,
	    0,   80,    0,   81,    0,   82,    0,   83,    0,   84,    0,   85,
	    0,   87,   86,   89,   88,   89,   90,   91,   92,   91,   93,   94,
	   95,   96,   90,   88,   89,   97,   88,   89,   98,   88,   89,   99,
	   88,   89,  100,   88,   89,  101,   88,   89,  102,   88,   89,  103,
	   88,   89,  104,   88,   89,  105,   88,   89,  106,   88,   89,  107,
	   88,   89,  108,   88,   89,  109,   88,   89,  110,   88,   89,  111,
	   88,   89,  112,   88,   89,  113,   88,   89,  114,   88,   89,  115,
	   88,   89,  116,   88,  118,  117,  119,  120,  121,  122,  123,  124,
	  125,  126,  127,  128,  129,  130,  131,  117,    0,  132,    0,  133,
	    0,  134,    0,  135,    0,  136,    0,  137,    0,  138,    0,  140,
	  139,  142,  141,  142,  143,  144,  145,  146,  144,  147,  148,  149,
	  150,  151,  143,  141,  142,  152,  141,  142,  153,  141,  142,  154,
	  141,  142,  155,  141,  142,  156,  141,  142,  157,  141,  142,  158,
	  141,  142,  159,  141,  142,  160,  141,  142,  161,  141,  142,  162,
	  141,  142,  163,  141,  142,  164,  141,  142,  165,  141,  142,  166,
	  141,  142,  166,  167,  168,  141,  142,  169,  141,  142,  170,  141,
	  142,  171,  141,  142,  172,  141,  142,  173,  141,  142,  174,  141,
	  142,  175,  141,  142,  176,  141,  142,  177,  141,  142,  178,  141,
	  142,  179,  141,  142,  180,  141,  142,  181,  141,  142,  182,  141,
	  142,  166,  141,  142,  183,  141,  142,  178,  141,  142,  184,  141,
	  142,  183,  141,  142,  185,  141,  142,  186,  141,  142,  187,  141,
	  142,  188,  141,  142,  189,  141,  142,  190,  141,  142,  191,  141,
	  142,  192,  166,  141,  142,  193,  141,  142,  194,  141,  142,  195,
	  141,  142,  196,  141,  142,  197,  141,  142,  198,  141,  142,  199,
	  141,  142,  200,  141,  142,  201,  141,  142,  182,  141,  142,  177,
	  141,  202,    0,  203,    0,  204,    0,  205,    0,  206,    0,  207,
	    0,  208,    0,  209,    0,  210,    0,  211,    0,  212,    0,  213,
	    0,  214,    0,  216,  215,  218,  217,  218,  219,  220,  221,  220,
	  219,  217,  218,  222,  217,  218,  223,  217,  218,  224,  217,  218,
	  225,  217,  218,  226,  217,  218,  227,  217,  228,    0,   80,    0,
	  229,    0,  228,    0,  230,    0,  231,    0,  232,    0,  233,    0,
	  234,    0,  235,    0,  236,    0,  237,  238,    0,  239,    0,  240,
	    0,  241,    0,  242,    0,  243,    0,  244,    0,  245,    0,  246,
	    0,  247,    0,  248,    0,  249,    0,  251,  250,  253,  252,  253,
	  254,  255,  256,  257,  255,  258,  259,  260,  261,  262,  254,  252,
	  253,  263,  252,  253,  264,  252,  253,  265,  252,  253,  266,  252,
	  253,  267,  252,  253,  268,  252,  253,  269,  252,  253,  270,  252,
	  253,  271,  252,  253,  272,  252,  253,  273,  252,  253,  274,  252,
	  253,  275,  252,  253,  276,  252,  253,  277,  252,  253,  277,  278,
	  279,  252,  253,  280,  252,  253,  281,  252,  253,  282,  252,  253,
	  283,  252,  253,  284,  252,  253,  285,  252,  253,  286,  252,  253,
	  287,  252,  253,  288,  252,  253,  289,  252,  253,  290,  252,  253,
	  291,  252,  253,  292,  252,  253,  293,  252,  253,  277,  252,  253,
	  294,  252,  253,  289,  252,  253,  295,  252,  253,  294,  252,  253,
	  296,  252,  253,  297,  252,  253,  298,  252,  253,  299,  252,  253,
	  300,  252,  253,  301,  252,  253,  293,  252,  253,  288,  252,  303,
	  302,  305,  304,  305,  306,  307,  308,  309,  307,  310,  311,  312,
	  313,  314,  315,  306,  304,  305,  316,  304,  305,  317,  304,  305,
	  318,  304,  305,  319,  304,  305,  320,  304,  305,  321,  304,  305,
	  322,  304,  305,  323,  304,  305,  324,  304,  305,  325,  304,  305,
	  326,  304,  305,  327,  304,  305,  328,  304,  305,  329,  304,  305,
	  330,  304,  305,  330,  331,  332,  304,  305,  333,  304,  305,  334,
	  304,  305,  335,  304,  305,  336,  304,  305,  337,  304,  305,  338,
	  304,  305,  339,  304,  305,  340,  304,  305,  341,  304,  305,  342,
	  304,  305,  343,  304,  305,  344,  304,  305,  345,  304,  305,  346,
	  304,  305,  330,  304,  305,  347,  304,  305,  348,  304,  305,  349,
	  304,  305,  350,  304,  305,  351,  304,  305,  346,  304,  305,  352,
	  304,  305,  342,  304,  305,  353,  304,  305,  352,  304,  305,  354,
	  304,  305,  355,  304,  305,  356,  304,  305,  357,  304,  305,  358,
	  304,  305,  359,  304,  305,  360,  304,  305,  361,  330,  304,  305,
	  362,  304,  305,  363,  304,  305,  364,  304,  305,  365,  304,  305,
	  366,  304,  305,  367,  304,  305,  368,  304,  305,  369,  304,  305,
	  370,  304,  305,  346,  304,  305,  341,  304,   79,    0,  371,  372,
	  371,    0,  375,  374,  376,  377,  374,  373,    0,  379,  380,  378,
	    0,  379,  378,  375,  381,  379,  380,  381,  378,  375,  382,  383,
	  384,  385,  386,  387,  388,  389,  390,  391,  392,  393,  394,  395,
	  382,    0,   89,  396,   88,   89,  397,   88,   89,  398,   88,   89,
	  399,   88,   89,  400,   88,   89,  115,   88,   89,  401,   88,   89,
	  402,   88,   89,  403,   88,   89,  404,   88,   89,  405,   88,   89,
	  406,   88,   89,  407,   88,   89,  408,   88,   89,  409,   88,   89,
	  410,   88,   89,  411,   88,   89,  115,   88,   89,  412,   88,   89,
	  413,   88,   89,  414,   88,   89,  415,   88,   89,  416,   88,   89,
	  417,   88,   89,  418,   88,   89,  419,  116,   88,   89,  420,   88,
	   89,  421,   88,   89,  422,   88,   89,  423,   88,   89,  424,   88,
	   89,  425,   88,   89,  426,   88,   89,  427,   88,   89,  428,   88,
	   89,  115,   88,  429,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   77,  141,
	  163,  165,  167,  295,  296,    3,    6,    7,    8,    9,    8,    8,
	    9,    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,
	   14,    4,   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,
	   24,   25,   26,   27,   28,   29,   30,  338,   32,   33,    4,   16,
	   33,    4,   16,   35,   36,    4,   35,   34,   36,   38,   48,   39,
	   40,   41,   42,   43,   44,   45,   46,   47,   31,   49,   50,   51,
	   52,   53,   54,   55,   54,   55,   55,    4,   56,   70,  302,  308,
	  320,   57,   58,   59,   60,   61,   62,   63,   64,   65,   66,   67,
	   68,   69,    4,   71,   72,   73,   74,   75,   76,    4,    4,    5,
	   15,   17,   31,   34,   37,   77,  141,  163,  165,  167,  295,  296,
	   78,   79,   80,   81,   82,   83,   84,   85,   86,   85,   86,   86,
	    4,   87,  101,  102,  118,  120,  122,  140,   88,   89,   90,   91,
	   92,   93,   94,   95,   96,   97,   98,   99,  100,    4,   76,  103,
	  113,  104,  105,  106,  107,  108,  109,  110,  111,  112,  101,  114,
	  115,  116,  117,  119,  121,  123,  124,  125,  126,  127,  128,  129,
	  130,  131,  132,  133,  134,  135,  136,  137,  138,  139,  142,  143,
	  144,  145,  146,  147,  148,  149,  150,  151,  152,  153,  154,  155,
	  156,  155,  156,  156,    4,  157,  158,  159,  160,  161,  162,   76,
	  164,  166,  168,  169,  170,  171,  172,  173,  174,  175,  232,  176,
	  177,  178,  179,  180,  181,  182,  183,  184,  185,  186,  187,  188,
	  187,  188,  188,    4,  189,  203,  204,  220,  222,  224,  231,  190,
	  191,  192,  193,  194,  195,  196,  197,  198,  199,  200,  201,  202,
	    4,   76,  205,  215,  206,  207,  208,  209,  210,  211,  212,  213,
	  214,  203,  216,  217,  218,  219,  221,  223,  225,  226,  227,  228,
	  229,  230,  233,  234,  233,  234,  234,    4,  235,  249,  250,  266,
	  272,  274,  276,  294,  236,  237,  238,  239,  240,  241,  242,  243,
	  244,  245,  246,  247,  248,    4,   76,  251,  261,  252,  253,  254,
	  255,  256,  257,  258,  259,  260,  249,  262,  263,  264,  265,  267,
	  268,  269,  270,  271,  273,  275,  277,  278,  279,  280,  281,  282,
	  283,  284,  285,  286,  287,  288,  289,  290,  291,  292,  293,  296,
	  297,  298,  300,  301,  299,  297,  298,  299,  297,  300,  301,    5,
	   15,   17,   31,   34,   37,   77,  141,  163,  165,  167,  295,  296,
	  303,  304,  305,  306,  307,  309,  310,  311,  312,  313,  314,  315,
	  316,  317,  318,  319,  321,  322,  323,  324,  325,  326,  327,  328,
	  329,  330,  331,  332,  333,  334,  335,  336,  337,    0
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
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   69,   33,   84,   84,   84,
	   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   13,    0,    0,    0,    0,    0,   13,   31,  130,   60,
	   57,   31,   63,   57,   63,   63,   63,   63,   63,   63,   63,   66,
	    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,
	   72,   33,   84,   84,   84,   84,   84,   84,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   15,   15,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
	  144,    0,   54,    0,   81,   84,    0,    0,    0,    0,    0,   21,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,  144,
	    0,   54,    0,   78,   33,   84,   84,   84,   84,   84,   84,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	   19,   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   75,   33,   84,   84,   84,
	   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   17,   17,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   37,   37,   54,   37,   87,    0,    0,   39,    0,    0,   93,
	   90,   41,   96,   90,   96,   96,   96,   96,   96,   96,   96,   99,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0
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
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 338;

static final int lexer_en_main = 1;


// line 159 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"

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

    
// line 531 "java/src/main/java/gherkin/lexer/Cy_gb.java"
	{
	cs = lexer_start;
	}

// line 185 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
    
// line 538 "java/src/main/java/gherkin/lexer/Cy_gb.java"
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
// line 16 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replace("\\\"\\\"\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
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
// line 136 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 799 "java/src/main/java/gherkin/lexer/Cy_gb.java"
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
// line 140 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 831 "java/src/main/java/gherkin/lexer/Cy_gb.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/home/rob/dev/ws/ruby/gherkin/tasks/../ragel/i18n/cy_gb.java.rl"
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
