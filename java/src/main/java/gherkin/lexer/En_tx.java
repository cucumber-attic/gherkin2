
// line 1 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class En_tx implements Lexer {
  
// line 150 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"


  private final Listener listener;

  public En_tx(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/En_tx.java"
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
	    0,    0,   19,   20,   21,   39,   40,   41,   43,   45,   50,   55,
	   60,   65,   69,   73,   75,   76,   77,   78,   79,   80,   81,   82,
	   83,   84,   85,   86,   87,   88,   89,   90,   91,   93,   95,  100,
	  107,  112,  114,  115,  116,  117,  118,  119,  120,  121,  122,  123,
	  124,  139,  141,  143,  145,  147,  149,  151,  153,  155,  157,  159,
	  161,  163,  165,  167,  169,  187,  189,  190,  191,  192,  193,  194,
	  195,  196,  197,  198,  199,  200,  215,  217,  219,  221,  223,  225,
	  227,  229,  231,  233,  235,  237,  239,  241,  243,  245,  248,  250,
	  252,  254,  256,  258,  260,  262,  264,  266,  268,  270,  272,  274,
	  276,  278,  280,  282,  284,  286,  288,  290,  292,  294,  296,  298,
	  300,  302,  304,  306,  308,  310,  312,  314,  316,  318,  319,  320,
	  321,  322,  323,  324,  325,  326,  327,  328,  329,  330,  331,  332,
	  333,  334,  335,  342,  344,  346,  348,  350,  352,  354,  356,  357,
	  358,  359,  360,  361,  362,  363,  364,  365,  377,  379,  381,  383,
	  385,  387,  389,  391,  393,  395,  397,  399,  401,  403,  405,  407,
	  409,  411,  413,  415,  417,  419,  421,  423,  425,  427,  429,  431,
	  433,  435,  437,  439,  441,  443,  445,  447,  449,  451,  453,  455,
	  457,  459,  461,  463,  465,  467,  469,  471,  473,  475,  477,  479,
	  481,  482,  483,  484,  485,  486,  487,  488,  489,  490,  491,  492,
	  493,  494,  495,  510,  512,  514,  516,  518,  520,  522,  524,  526,
	  528,  530,  532,  534,  536,  538,  540,  543,  545,  547,  549,  551,
	  553,  555,  557,  559,  561,  563,  565,  567,  569,  571,  573,  576,
	  578,  580,  582,  584,  586,  588,  590,  592,  594,  596,  598,  600,
	  602,  604,  606,  608,  610,  612,  614,  616,  618,  620,  622,  624,
	  626,  628,  630,  631,  635,  641,  644,  646,  652,  670,  672,  674,
	  676,  678,  680,  682,  684,  686,  688,  690,  692,  694,  696,  698,
	  700,  702,  704,  706,  708,  710,  712,  714,  716,  718,  720,  722,
	  724,  726,  728,  729
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   65,   66,   69,   70,
	   71,   83,   84,   87,  124,    9,   13,  -69,  -65,   10,   32,   34,
	   35,   37,   42,   64,   65,   66,   69,   70,   71,   83,   84,   87,
	  124,    9,   13,   34,   34,   10,   13,   10,   13,   10,   32,   34,
	    9,   13,   10,   32,   34,    9,   13,   10,   32,   34,    9,   13,
	   10,   32,   34,    9,   13,   10,   32,    9,   13,   10,   32,    9,
	   13,   10,   13,   10,   95,   70,   69,   65,   84,   85,   82,   69,
	   95,   69,   78,   68,   95,   37,   32,   10,   13,   10,   13,   13,
	   32,   64,    9,   10,    9,   10,   13,   32,   64,   11,   12,   10,
	   32,   64,    9,   13,  108,  110,  108,   32,  121,   39,   97,  108,
	  108,   58,   10,   10,   10,   32,   35,   37,   42,   64,   65,   66,
	   70,   71,   83,   84,   87,    9,   13,   10,   95,   10,   70,   10,
	   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,
	   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,
	   32,   10,   32,   34,   35,   37,   42,   64,   65,   66,   69,   70,
	   71,   83,   84,   87,  124,    9,   13,   97,  117,   99,  107,  103,
	  114,  111,  117,  110,  100,   58,   10,   10,   10,   32,   35,   37,
	   42,   64,   65,   66,   70,   71,   83,   84,   87,    9,   13,   10,
	   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,   85,   10,
	   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,   68,   10,
	   95,   10,   37,   10,   32,   10,  108,  110,   10,  108,   10,   32,
	   10,  121,   10,   39,   10,   97,   10,  108,   10,  108,   10,   58,
	   10,  100,   10,   32,   10,  121,   10,   39,   10,   97,   10,  108,
	   10,  108,   10,  117,   10,  116,   10,  101,   10,   97,   10,  116,
	   10,  117,   10,  114,   10,  101,   10,  105,   10,  118,   10,  101,
	   10,  110,   10,   99,   10,  101,   10,  110,   10,   97,   10,  114,
	   10,  105,   10,  111,   10,  104,  116,   32,  121,   39,   97,  108,
	  108,  120,   97,  109,  112,  108,  101,  115,   58,   10,   10,   10,
	   32,   35,   70,  124,    9,   13,   10,  101,   10,   97,   10,  116,
	   10,  117,   10,  114,   10,  101,   10,   58,  101,   97,  116,  117,
	  114,  101,   58,   10,   10,   10,   32,   35,   37,   64,   65,   66,
	   69,   70,   83,    9,   13,   10,   95,   10,   70,   10,   69,   10,
	   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,   10,
	   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,  108,   10,
	  108,   10,   32,   10,  121,   10,   39,   10,   97,   10,  108,   10,
	  108,   10,   58,   10,   97,   10,   99,   10,  107,   10,  103,   10,
	  114,   10,  111,   10,  117,   10,  110,   10,  100,   10,  120,   10,
	   97,   10,  109,   10,  112,   10,  108,   10,  101,   10,  115,   10,
	  101,   10,   97,   10,  116,   10,  117,   10,  114,   10,  101,   10,
	   99,   10,  101,   10,  110,   10,   97,   10,  114,   10,  105,   10,
	  111,  105,  118,  101,  110,   99,  101,  110,   97,  114,  105,  111,
	   58,   10,   10,   10,   32,   35,   37,   42,   64,   65,   66,   70,
	   71,   83,   84,   87,    9,   13,   10,   95,   10,   70,   10,   69,
	   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,
	   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,   32,
	   10,  108,  110,   10,  108,   10,   32,   10,  121,   10,   39,   10,
	   97,   10,  108,   10,  108,   10,   58,   10,  100,   10,   32,   10,
	  121,   10,   39,   10,   97,   10,  108,   10,  108,   10,   97,  117,
	   10,   99,   10,  107,   10,  103,   10,  114,   10,  111,   10,  117,
	   10,  110,   10,  100,   10,  116,   10,  101,   10,   97,   10,  116,
	   10,  117,   10,  114,   10,  101,   10,  105,   10,  118,   10,  101,
	   10,  110,   10,   99,   10,  101,   10,  110,   10,   97,   10,  114,
	   10,  105,   10,  111,   10,  104,  104,   32,  124,    9,   13,   10,
	   32,   92,  124,    9,   13,   10,   92,  124,   10,   92,   10,   32,
	   92,  124,    9,   13,   10,   32,   34,   35,   37,   42,   64,   65,
	   66,   69,   70,   71,   83,   84,   87,  124,    9,   13,   10,  110,
	   10,  100,   10,   32,   10,  121,   10,   39,   10,   97,   10,  108,
	   10,  108,   10,  117,   10,  116,   10,  101,   10,   97,   10,  116,
	   10,  117,   10,  114,   10,  101,   10,   58,   10,  105,   10,  118,
	   10,  101,   10,  110,   10,   99,   10,  101,   10,  110,   10,   97,
	   10,  114,   10,  105,   10,  111,   10,  104,  100,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   17,    1,    1,   16,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	   13,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,   16,    2,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,   13,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    5,    2,    2,    2,    2,    2,    2,    2,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,   10,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,   13,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    1,    2,    4,    3,    2,    4,   16,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    1,    0
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
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    1,    1,    0,    0,    1,    1,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0
	};
}

private static final byte _lexer_range_lengths[] = init__lexer_range_lengths_0();


private static short[] init__lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,   19,   21,   23,   41,   43,   45,   48,   51,   56,   61,
	   66,   71,   75,   79,   82,   84,   86,   88,   90,   92,   94,   96,
	   98,  100,  102,  104,  106,  108,  110,  112,  114,  117,  120,  125,
	  132,  137,  140,  142,  144,  146,  148,  150,  152,  154,  156,  158,
	  160,  175,  178,  181,  184,  187,  190,  193,  196,  199,  202,  205,
	  208,  211,  214,  217,  220,  238,  241,  243,  245,  247,  249,  251,
	  253,  255,  257,  259,  261,  263,  278,  281,  284,  287,  290,  293,
	  296,  299,  302,  305,  308,  311,  314,  317,  320,  323,  327,  330,
	  333,  336,  339,  342,  345,  348,  351,  354,  357,  360,  363,  366,
	  369,  372,  375,  378,  381,  384,  387,  390,  393,  396,  399,  402,
	  405,  408,  411,  414,  417,  420,  423,  426,  429,  432,  434,  436,
	  438,  440,  442,  444,  446,  448,  450,  452,  454,  456,  458,  460,
	  462,  464,  466,  473,  476,  479,  482,  485,  488,  491,  494,  496,
	  498,  500,  502,  504,  506,  508,  510,  512,  524,  527,  530,  533,
	  536,  539,  542,  545,  548,  551,  554,  557,  560,  563,  566,  569,
	  572,  575,  578,  581,  584,  587,  590,  593,  596,  599,  602,  605,
	  608,  611,  614,  617,  620,  623,  626,  629,  632,  635,  638,  641,
	  644,  647,  650,  653,  656,  659,  662,  665,  668,  671,  674,  677,
	  680,  682,  684,  686,  688,  690,  692,  694,  696,  698,  700,  702,
	  704,  706,  708,  723,  726,  729,  732,  735,  738,  741,  744,  747,
	  750,  753,  756,  759,  762,  765,  768,  772,  775,  778,  781,  784,
	  787,  790,  793,  796,  799,  802,  805,  808,  811,  814,  817,  821,
	  824,  827,  830,  833,  836,  839,  842,  845,  848,  851,  854,  857,
	  860,  863,  866,  869,  872,  875,  878,  881,  884,  887,  890,  893,
	  896,  899,  902,  904,  908,  914,  918,  921,  927,  945,  948,  951,
	  954,  957,  960,  963,  966,  969,  972,  975,  978,  981,  984,  987,
	  990,  993,  996,  999, 1002, 1005, 1008, 1011, 1014, 1017, 1020, 1023,
	 1026, 1029, 1032, 1034
	};
}

private static final short _lexer_index_offsets[] = init__lexer_index_offsets_0();


private static short[] init__lexer_indicies_0()
{
	return new short [] {
	    1,    3,    2,    4,    5,    6,    7,    8,    9,   10,   11,   12,
	   13,   14,   15,   15,   16,    2,    0,   17,    0,    2,    0,    3,
	    2,    4,    5,    6,    7,    8,    9,   10,   11,   12,   13,   14,
	   15,   15,   16,    2,    0,   18,    0,   19,    0,   21,   22,   20,
	   24,   25,   23,   28,   27,   29,   27,   26,   32,   31,   33,   31,
	   30,   32,   31,   34,   31,   30,   32,   31,   35,   31,   30,   37,
	   36,   36,    0,    3,   38,   38,    0,   40,   41,   39,    3,    0,
	   42,    0,   43,    0,   44,    0,   45,    0,   46,    0,   47,    0,
	   48,    0,   49,    0,   50,    0,   51,    0,   52,    0,   53,    0,
	   54,    0,   55,    0,   56,    0,   58,   59,   57,   61,   62,   60,
	    0,    0,    0,    0,   63,   64,   65,   64,   64,   67,   66,   63,
	    3,   68,    8,   68,    0,   69,   70,    0,   71,    0,   72,    0,
	   73,    0,   74,    0,   75,    0,   76,    0,   77,    0,   78,    0,
	   80,   79,   82,   81,   82,   83,   84,   85,   86,   84,   87,   88,
	   89,   90,   91,   92,   92,   83,   81,   82,   93,   81,   82,   94,
	   81,   82,   95,   81,   82,   96,   81,   82,   97,   81,   82,   98,
	   81,   82,   99,   81,   82,  100,   81,   82,  101,   81,   82,  102,
	   81,   82,  103,   81,   82,  104,   81,   82,  105,   81,   82,  106,
	   81,   82,  107,   81,  109,  108,  110,  111,  112,  113,  114,  115,
	  116,  117,  118,  119,  120,  121,  121,  122,  108,    0,  123,  124,
	    0,  125,    0,  126,    0,  127,    0,  128,    0,  129,    0,  130,
	    0,  131,    0,  132,    0,  133,    0,  135,  134,  137,  136,  137,
	  138,  139,  140,  141,  139,  142,  143,  144,  145,  146,  147,  147,
	  138,  136,  137,  148,  136,  137,  149,  136,  137,  150,  136,  137,
	  151,  136,  137,  152,  136,  137,  153,  136,  137,  154,  136,  137,
	  155,  136,  137,  156,  136,  137,  157,  136,  137,  158,  136,  137,
	  159,  136,  137,  160,  136,  137,  161,  136,  137,  162,  136,  137,
	  163,  164,  136,  137,  165,  136,  137,  166,  136,  137,  167,  136,
	  137,  168,  136,  137,  169,  136,  137,  170,  136,  137,  171,  136,
	  137,  162,  136,  137,  172,  136,  137,  173,  136,  137,  174,  136,
	  137,  175,  136,  137,  176,  136,  137,  177,  136,  137,  178,  136,
	  137,  179,  136,  137,  172,  136,  137,  180,  136,  137,  181,  136,
	  137,  182,  136,  137,  183,  136,  137,  184,  136,  137,  171,  136,
	  137,  185,  136,  137,  186,  136,  137,  187,  136,  137,  172,  136,
	  137,  188,  136,  137,  189,  136,  137,  190,  136,  137,  191,  136,
	  137,  192,  136,  137,  193,  136,  137,  171,  136,  137,  186,  136,
	  194,    0,  195,    0,  196,    0,  197,    0,  198,    0,  199,    0,
	  200,    0,  201,    0,  202,    0,  203,    0,  204,    0,  205,    0,
	  206,    0,  207,    0,  208,    0,  210,  209,  212,  211,  212,  213,
	  214,  215,  214,  213,  211,  212,  216,  211,  212,  217,  211,  212,
	  218,  211,  212,  219,  211,  212,  220,  211,  212,  221,  211,  212,
	  222,  211,  223,    0,  224,    0,  225,    0,  226,    0,  227,    0,
	  228,    0,  229,    0,  231,  230,  233,  232,  233,  234,  235,  236,
	  235,  237,  238,  239,  240,  241,  234,  232,  233,  242,  232,  233,
	  243,  232,  233,  244,  232,  233,  245,  232,  233,  246,  232,  233,
	  247,  232,  233,  248,  232,  233,  249,  232,  233,  250,  232,  233,
	  251,  232,  233,  252,  232,  233,  253,  232,  233,  254,  232,  233,
	  255,  232,  233,  256,  232,  233,  257,  232,  233,  258,  232,  233,
	  259,  232,  233,  260,  232,  233,  261,  232,  233,  262,  232,  233,
	  263,  232,  233,  264,  232,  233,  265,  232,  233,  266,  232,  233,
	  267,  232,  233,  268,  232,  233,  269,  232,  233,  270,  232,  233,
	  271,  232,  233,  272,  232,  233,  263,  232,  233,  273,  232,  233,
	  274,  232,  233,  275,  232,  233,  276,  232,  233,  277,  232,  233,
	  278,  232,  233,  263,  232,  233,  279,  232,  233,  280,  232,  233,
	  281,  232,  233,  282,  232,  233,  283,  232,  233,  263,  232,  233,
	  284,  232,  233,  285,  232,  233,  286,  232,  233,  287,  232,  233,
	  288,  232,  233,  289,  232,  233,  263,  232,  290,    0,  291,    0,
	  292,    0,  194,    0,  293,    0,  294,    0,  295,    0,  296,    0,
	  297,    0,  298,    0,  299,    0,  300,    0,  302,  301,  304,  303,
	  304,  305,  306,  307,  308,  306,  309,  310,  311,  312,  313,  314,
	  314,  305,  303,  304,  315,  303,  304,  316,  303,  304,  317,  303,
	  304,  318,  303,  304,  319,  303,  304,  320,  303,  304,  321,  303,
	  304,  322,  303,  304,  323,  303,  304,  324,  303,  304,  325,  303,
	  304,  326,  303,  304,  327,  303,  304,  328,  303,  304,  329,  303,
	  304,  330,  331,  303,  304,  332,  303,  304,  333,  303,  304,  334,
	  303,  304,  335,  303,  304,  336,  303,  304,  337,  303,  304,  338,
	  303,  304,  329,  303,  304,  339,  303,  304,  340,  303,  304,  341,
	  303,  304,  342,  303,  304,  343,  303,  304,  344,  303,  304,  345,
	  303,  304,  346,  347,  303,  304,  348,  303,  304,  349,  303,  304,
	  350,  303,  304,  351,  303,  304,  352,  303,  304,  353,  303,  304,
	  354,  303,  304,  338,  303,  304,  339,  303,  304,  355,  303,  304,
	  356,  303,  304,  357,  303,  304,  358,  303,  304,  359,  303,  304,
	  338,  303,  304,  360,  303,  304,  361,  303,  304,  362,  303,  304,
	  339,  303,  304,  363,  303,  304,  364,  303,  304,  365,  303,  304,
	  366,  303,  304,  367,  303,  304,  368,  303,  304,  338,  303,  304,
	  361,  303,  291,    0,  369,  370,  369,    0,  373,  372,  374,  375,
	  372,  371,    0,  377,  378,  376,    0,  377,  376,  373,  379,  377,
	  378,  379,  376,  373,  380,  381,  382,  383,  384,  385,  386,  387,
	  388,  389,  390,  391,  392,  392,  393,  380,    0,   82,  394,   81,
	   82,  395,   81,   82,  396,   81,   82,  397,   81,   82,  398,   81,
	   82,  399,   81,   82,  400,   81,   82,  401,   81,   82,  402,   81,
	   82,  395,   81,   82,  403,   81,   82,  404,   81,   82,  405,   81,
	   82,  406,   81,   82,  407,   81,   82,  408,   81,   82,  107,   81,
	   82,  409,   81,   82,  410,   81,   82,  411,   81,   82,  395,   81,
	   82,  412,   81,   82,  413,   81,   82,  414,   81,   82,  415,   81,
	   82,  416,   81,   82,  417,   81,   82,  408,   81,   82,  410,   81,
	  194,    0,  418,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   65,  136,
	  154,  216,  220,  290,  291,    3,    6,    7,    8,    9,    8,    8,
	    9,    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,
	   14,    4,   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,
	   24,   25,   26,   27,   28,   29,   30,  327,   32,   33,    4,   16,
	   33,    4,   16,   35,   36,    4,   35,   34,   36,   38,  326,   39,
	   40,   41,   42,   43,   44,   45,   46,   47,   48,   47,   48,   48,
	    4,   49,   63,  297,  305,  307,  314,  318,  325,   50,   51,   52,
	   53,   54,   55,   56,   57,   58,   59,   60,   61,   62,    4,   64,
	    4,    4,    5,   15,   17,   31,   34,   37,   65,  136,  154,  216,
	  220,  290,  291,   66,  129,   67,   68,   69,   70,   71,   72,   73,
	   74,   75,   76,   77,   76,   77,   77,    4,   78,   92,   93,  109,
	  111,  117,  121,  128,   79,   80,   81,   82,   83,   84,   85,   86,
	   87,   88,   89,   90,   91,    4,   64,   94,  102,   95,   96,   97,
	   98,   99,  100,  101,  103,  104,  105,  106,  107,  108,   92,  110,
	  112,  113,  114,  115,  116,  118,  119,  120,  122,  123,  124,  125,
	  126,  127,  130,  131,  132,  133,  134,  135,   31,  137,  138,  139,
	  140,  141,  142,  143,  144,  145,  146,  145,  146,  146,    4,  147,
	  148,  149,  150,  151,  152,  153,   64,  155,  156,  157,  158,  159,
	  160,  161,  162,  163,  162,  163,  163,    4,  164,  178,  187,  196,
	  203,  209,  165,  166,  167,  168,  169,  170,  171,  172,  173,  174,
	  175,  176,  177,    4,  179,  180,  181,  182,  183,  184,  185,  186,
	   64,  188,  189,  190,  191,  192,  193,  194,  195,  197,  198,  199,
	  200,  201,  202,  204,  205,  206,  207,  208,  210,  211,  212,  213,
	  214,  215,  217,  218,  219,  221,  222,  223,  224,  225,  226,  227,
	  228,  229,  230,  229,  230,  230,    4,  231,  245,  246,  262,  272,
	  278,  282,  289,  232,  233,  234,  235,  236,  237,  238,  239,  240,
	  241,  242,  243,  244,    4,   64,  247,  255,  248,  249,  250,  251,
	  252,  253,  254,  256,  257,  258,  259,  260,  261,  245,  263,  271,
	  264,  265,  266,  267,  268,  269,  270,  273,  274,  275,  276,  277,
	  279,  280,  281,  283,  284,  285,  286,  287,  288,  291,  292,  293,
	  295,  296,  294,  292,  293,  294,  292,  295,  296,    5,   15,   17,
	   31,   34,   37,   65,  136,  154,  216,  220,  290,  291,  298,  299,
	  300,  301,  302,  303,  304,   63,  306,  308,  309,  310,  311,  312,
	  313,  315,  316,  317,  319,  320,  321,  322,  323,  324,    0
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
	    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,
	   78,   33,   84,   84,   84,   84,   84,   84,   84,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   19,   19,
	   31,  130,   60,   57,   31,   63,   57,   63,   63,   63,   63,   63,
	   63,   63,   66,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   72,   33,   84,   84,   84,
	   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   15,   15,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   81,   84,
	    0,    0,    0,    0,    0,    0,   21,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   69,   33,   84,   84,   84,
	   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,   13,    0,    0,    0,    0,    0,    0,    0,    0,
	   13,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   57,  144,    0,   54,    0,   75,   33,   84,   84,   84,   84,
	   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,   17,   17,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   37,
	   37,   54,   37,   87,    0,    0,   39,    0,    0,   93,   90,   41,
	   96,   90,   96,   96,   96,   96,   96,   96,   96,   99,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0
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
	   43,   43,   43,   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 327;

static final int lexer_en_main = 1;


// line 159 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"

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

    
// line 521 "java/src/main/java/gherkin/lexer/En_tx.java"
	{
	cs = lexer_start;
	}

// line 185 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
    
// line 528 "java/src/main/java/gherkin/lexer/En_tx.java"
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
// line 16 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replace("\\\"\\\"\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
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
// line 136 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 789 "java/src/main/java/gherkin/lexer/En_tx.java"
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
// line 140 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 821 "java/src/main/java/gherkin/lexer/En_tx.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/en_tx.java.rl"
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
