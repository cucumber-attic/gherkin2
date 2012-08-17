
// line 1 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class Et implements Lexer {
  
// line 150 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"


  private final Listener listener;

  public Et(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/Et.java"
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
	  105,  110,  111,  112,  113,  114,  115,  116,  117,  119,  120,  121,
	  122,  123,  124,  125,  126,  127,  128,  135,  137,  139,  141,  143,
	  145,  147,  164,  165,  166,  168,  169,  170,  171,  172,  173,  174,
	  175,  176,  188,  190,  192,  194,  196,  198,  200,  202,  204,  206,
	  208,  210,  212,  214,  216,  218,  220,  222,  224,  226,  228,  230,
	  232,  234,  236,  238,  240,  242,  244,  246,  248,  250,  252,  254,
	  256,  258,  260,  262,  264,  266,  268,  270,  272,  274,  276,  278,
	  279,  280,  281,  282,  283,  284,  285,  286,  287,  288,  289,  290,
	  291,  292,  293,  294,  295,  308,  310,  312,  314,  316,  318,  320,
	  322,  324,  326,  328,  330,  332,  334,  336,  338,  340,  342,  344,
	  346,  348,  350,  352,  354,  356,  358,  361,  363,  365,  367,  369,
	  371,  373,  376,  378,  380,  382,  384,  386,  388,  390,  392,  394,
	  396,  398,  399,  400,  401,  402,  403,  404,  405,  406,  407,  408,
	  409,  410,  411,  426,  428,  430,  432,  434,  436,  438,  440,  442,
	  444,  446,  448,  450,  452,  454,  456,  458,  460,  462,  464,  466,
	  468,  470,  472,  474,  476,  479,  481,  483,  485,  487,  489,  491,
	  493,  495,  497,  499,  501,  503,  505,  507,  509,  511,  513,  515,
	  517,  519,  522,  524,  526,  528,  530,  532,  533,  534,  535,  536,
	  537,  538,  539,  553,  555,  557,  559,  561,  563,  565,  567,  569,
	  571,  573,  575,  577,  579,  581,  583,  585,  587,  589,  591,  593,
	  595,  597,  599,  601,  603,  606,  608,  610,  612,  614,  616,  618,
	  620,  622,  624,  626,  628,  630,  632,  634,  636,  638,  640,  642,
	  644,  646,  649,  651,  655,  661,  664,  666,  672,  689
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   69,   74,   75,   79,
	   82,   83,   84,  124,    9,   13,  -69,  -65,   10,   32,   34,   35,
	   37,   42,   64,   69,   74,   75,   79,   82,   83,   84,  124,    9,
	   13,   34,   34,   10,   13,   10,   13,   10,   32,   34,    9,   13,
	   10,   32,   34,    9,   13,   10,   32,   34,    9,   13,   10,   32,
	   34,    9,   13,   10,   32,    9,   13,   10,   32,    9,   13,   10,
	   13,   10,   95,   70,   69,   65,   84,   85,   82,   69,   95,   69,
	   78,   68,   95,   37,   32,   10,   13,   10,   13,   13,   32,   64,
	    9,   10,    9,   10,   13,   32,   64,   11,   12,   10,   32,   64,
	    9,   13,  101,  108,  100,   97,  100,  101,  115,   97,  117,  104,
	  116,  117,  109,  105,  100,   58,   10,   10,   10,   32,   35,   79,
	  124,    9,   13,   10,  109,   10,   97,   10,  100,   10,  117,   10,
	  115,   10,   58,   10,   32,   34,   35,   37,   42,   64,   69,   74,
	   75,   79,   82,   83,   84,  124,    9,   13,  117,  105,   32,  100,
	  109,   97,  100,  117,  115,   58,   10,   10,   10,   32,   35,   37,
	   64,   74,   79,   82,   83,   84,    9,   13,   10,   95,   10,   70,
	   10,   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,
	   10,   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,
	   10,  117,   10,  104,   10,  116,   10,  117,   10,  109,   10,  105,
	   10,  100,   10,   58,   10,  109,   10,   97,   10,  100,   10,  117,
	   10,  115,   10,   97,   10,   97,   10,  109,   10,  115,   10,  116,
	   10,  115,   10,  101,   10,  110,   10,   97,   10,   97,   10,  114,
	   10,  105,   10,  117,   10,  109,   10,   97,   10,  117,   10,  115,
	   10,  116,   97,   97,  109,  115,  116,  115,  101,  110,   97,   97,
	  114,  105,  117,  109,   58,   10,   10,   10,   32,   35,   37,   42,
	   64,   69,   74,   75,   79,   83,    9,   13,   10,   95,   10,   70,
	   10,   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,
	   10,   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,
	   10,   32,   10,  101,   10,  108,   10,  100,   10,   97,   10,  100,
	   10,  101,   10,  115,   10,   97,   10,  117,   10,  105,   10,   32,
	  100,   10,  109,   10,   97,   10,  100,   10,  117,   10,  115,   10,
	   58,   10,  105,  116,   10,  105,   10,  115,   10,  101,   10,  110,
	   10,   97,   10,   97,   10,  114,   10,  105,   10,  117,   10,  109,
	  105,  116,  105,  115,  101,  110,   97,   97,  114,  105,  117,  109,
	   58,   10,   10,   10,   32,   35,   37,   42,   64,   69,   74,   75,
	   79,   82,   83,   84,    9,   13,   10,   95,   10,   70,   10,   69,
	   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,
	   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,   32,
	   10,  101,   10,  108,   10,  100,   10,   97,   10,  100,   10,  101,
	   10,  115,   10,   97,   10,  117,   10,  105,   10,   32,  100,   10,
	  109,   10,   97,   10,  100,   10,  117,   10,  115,   10,   58,   10,
	   97,   10,   97,   10,  109,   10,  115,   10,  116,   10,  115,   10,
	  101,   10,  110,   10,   97,   10,   97,   10,  114,   10,  105,   10,
	  117,   10,  109,   10,  105,  116,   10,  105,   10,   97,   10,  117,
	   10,  115,   10,  116,   97,  117,  115,  116,   58,   10,   10,   10,
	   32,   35,   37,   42,   64,   69,   74,   75,   79,   82,   83,    9,
	   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,
	   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,
	   68,   10,   95,   10,   37,   10,   32,   10,  101,   10,  108,   10,
	  100,   10,   97,   10,  100,   10,  101,   10,  115,   10,   97,   10,
	  117,   10,  105,   10,   32,  100,   10,  109,   10,   97,   10,  100,
	   10,  117,   10,  115,   10,   58,   10,   97,   10,   97,   10,  109,
	   10,  115,   10,  116,   10,  115,   10,  101,   10,  110,   10,   97,
	   10,   97,   10,  114,   10,  105,   10,  117,   10,  109,   10,  105,
	  116,   10,  105,   32,  124,    9,   13,   10,   32,   92,  124,    9,
	   13,   10,   92,  124,   10,   92,   10,   32,   92,  124,    9,   13,
	   10,   32,   34,   35,   37,   42,   64,   69,   74,   75,   79,   82,
	   83,   84,  124,    9,   13,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   16,    1,    1,   15,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    1,    1,    1,    1,    1,    1,    1,    2,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    5,    2,    2,    2,    2,    2,
	    2,   15,    1,    1,    2,    1,    1,    1,    1,    1,    1,    1,
	    1,   10,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,   11,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,
	    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,   13,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    3,    2,    2,    2,    2,    2,    1,    1,    1,    1,    1,
	    1,    1,   12,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    3,    2,    2,    4,    3,    2,    4,   15,    0
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
	    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    1,    1,    0,    0,    1,    1,    0
	};
}

private static final byte _lexer_range_lengths[] = init__lexer_range_lengths_0();


private static short[] init__lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,   18,   20,   22,   39,   41,   43,   46,   49,   54,   59,
	   64,   69,   73,   77,   80,   82,   84,   86,   88,   90,   92,   94,
	   96,   98,  100,  102,  104,  106,  108,  110,  112,  115,  118,  123,
	  130,  135,  137,  139,  141,  143,  145,  147,  149,  152,  154,  156,
	  158,  160,  162,  164,  166,  168,  170,  177,  180,  183,  186,  189,
	  192,  195,  212,  214,  216,  219,  221,  223,  225,  227,  229,  231,
	  233,  235,  247,  250,  253,  256,  259,  262,  265,  268,  271,  274,
	  277,  280,  283,  286,  289,  292,  295,  298,  301,  304,  307,  310,
	  313,  316,  319,  322,  325,  328,  331,  334,  337,  340,  343,  346,
	  349,  352,  355,  358,  361,  364,  367,  370,  373,  376,  379,  382,
	  384,  386,  388,  390,  392,  394,  396,  398,  400,  402,  404,  406,
	  408,  410,  412,  414,  416,  429,  432,  435,  438,  441,  444,  447,
	  450,  453,  456,  459,  462,  465,  468,  471,  474,  477,  480,  483,
	  486,  489,  492,  495,  498,  501,  504,  508,  511,  514,  517,  520,
	  523,  526,  530,  533,  536,  539,  542,  545,  548,  551,  554,  557,
	  560,  563,  565,  567,  569,  571,  573,  575,  577,  579,  581,  583,
	  585,  587,  589,  604,  607,  610,  613,  616,  619,  622,  625,  628,
	  631,  634,  637,  640,  643,  646,  649,  652,  655,  658,  661,  664,
	  667,  670,  673,  676,  679,  683,  686,  689,  692,  695,  698,  701,
	  704,  707,  710,  713,  716,  719,  722,  725,  728,  731,  734,  737,
	  740,  743,  747,  750,  753,  756,  759,  762,  764,  766,  768,  770,
	  772,  774,  776,  790,  793,  796,  799,  802,  805,  808,  811,  814,
	  817,  820,  823,  826,  829,  832,  835,  838,  841,  844,  847,  850,
	  853,  856,  859,  862,  865,  869,  872,  875,  878,  881,  884,  887,
	  890,  893,  896,  899,  902,  905,  908,  911,  914,  917,  920,  923,
	  926,  929,  933,  936,  940,  946,  950,  953,  959,  976
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
	    0,   74,    0,   75,    0,   75,   76,    0,   77,    0,   78,    0,
	   79,    0,   80,    0,   81,    0,   82,    0,   83,    0,   85,   84,
	   87,   86,   87,   88,   89,   90,   89,   88,   86,   87,   91,   86,
	   87,   92,   86,   87,   93,   86,   87,   94,   86,   87,   95,   86,
	   87,   96,   86,   98,   97,   99,  100,  101,  102,  103,  104,  105,
	  106,  107,  108,  109,  110,  111,   97,    0,  112,    0,  113,    0,
	   56,   75,    0,  114,    0,  115,    0,  116,    0,  117,    0,  118,
	    0,  119,    0,  121,  120,  123,  122,  123,  124,  125,  126,  125,
	  127,  128,  129,  130,  131,  124,  122,  123,  132,  122,  123,  133,
	  122,  123,  134,  122,  123,  135,  122,  123,  136,  122,  123,  137,
	  122,  123,  138,  122,  123,  139,  122,  123,  140,  122,  123,  141,
	  122,  123,  142,  122,  123,  143,  122,  123,  144,  122,  123,  145,
	  122,  123,  146,  122,  123,  147,  122,  123,  148,  122,  123,  149,
	  122,  123,  150,  122,  123,  151,  122,  123,  152,  122,  123,  153,
	  122,  123,  154,  122,  123,  155,  122,  123,  156,  122,  123,  157,
	  122,  123,  152,  122,  123,  158,  122,  123,  159,  122,  123,  160,
	  122,  123,  161,  122,  123,  162,  122,  123,  163,  122,  123,  164,
	  122,  123,  165,  122,  123,  166,  122,  123,  167,  122,  123,  168,
	  122,  123,  169,  122,  123,  170,  122,  123,  152,  122,  123,  171,
	  122,  123,  172,  122,  123,  173,  122,  123,  152,  122,  174,    0,
	  175,    0,  176,    0,  177,    0,  178,    0,  179,    0,  180,    0,
	  181,    0,  182,    0,  183,    0,  184,    0,  185,    0,  186,    0,
	  187,    0,  188,    0,  190,  189,  192,  191,  192,  193,  194,  195,
	  196,  194,  197,  198,  199,  200,  201,  193,  191,  192,  202,  191,
	  192,  203,  191,  192,  204,  191,  192,  205,  191,  192,  206,  191,
	  192,  207,  191,  192,  208,  191,  192,  209,  191,  192,  210,  191,
	  192,  211,  191,  192,  212,  191,  192,  213,  191,  192,  214,  191,
	  192,  215,  191,  192,  216,  191,  192,  217,  191,  192,  218,  191,
	  192,  219,  191,  192,  220,  191,  192,  221,  191,  192,  222,  191,
	  192,  223,  191,  192,  223,  191,  192,  224,  191,  192,  225,  191,
	  192,  216,  223,  191,  192,  226,  191,  192,  227,  191,  192,  228,
	  191,  192,  229,  191,  192,  230,  191,  192,  216,  191,  192,  231,
	  232,  191,  192,  222,  191,  192,  233,  191,  192,  234,  191,  192,
	  235,  191,  192,  236,  191,  192,  237,  191,  192,  238,  191,  192,
	  239,  191,  192,  240,  191,  192,  230,  191,  241,  242,    0,   74,
	    0,  243,    0,  244,    0,  245,    0,  246,    0,  247,    0,  248,
	    0,  249,    0,  250,    0,  251,    0,  252,    0,  254,  253,  256,
	  255,  256,  257,  258,  259,  260,  258,  261,  262,  263,  264,  265,
	  266,  267,  257,  255,  256,  268,  255,  256,  269,  255,  256,  270,
	  255,  256,  271,  255,  256,  272,  255,  256,  273,  255,  256,  274,
	  255,  256,  275,  255,  256,  276,  255,  256,  277,  255,  256,  278,
	  255,  256,  279,  255,  256,  280,  255,  256,  281,  255,  256,  282,
	  255,  256,  283,  255,  256,  284,  255,  256,  285,  255,  256,  286,
	  255,  256,  287,  255,  256,  288,  255,  256,  289,  255,  256,  289,
	  255,  256,  290,  255,  256,  291,  255,  256,  282,  289,  255,  256,
	  292,  255,  256,  293,  255,  256,  294,  255,  256,  295,  255,  256,
	  296,  255,  256,  282,  255,  256,  297,  255,  256,  298,  255,  256,
	  299,  255,  256,  300,  255,  256,  301,  255,  256,  302,  255,  256,
	  303,  255,  256,  304,  255,  256,  305,  255,  256,  306,  255,  256,
	  307,  255,  256,  308,  255,  256,  309,  255,  256,  296,  255,  256,
	  310,  301,  255,  256,  288,  255,  256,  311,  255,  256,  312,  255,
	  256,  313,  255,  256,  296,  255,  314,    0,  315,    0,  316,    0,
	  317,    0,  318,    0,  320,  319,  322,  321,  322,  323,  324,  325,
	  326,  324,  327,  328,  329,  330,  331,  332,  323,  321,  322,  333,
	  321,  322,  334,  321,  322,  335,  321,  322,  336,  321,  322,  337,
	  321,  322,  338,  321,  322,  339,  321,  322,  340,  321,  322,  341,
	  321,  322,  342,  321,  322,  343,  321,  322,  344,  321,  322,  345,
	  321,  322,  346,  321,  322,  347,  321,  322,  348,  321,  322,  349,
	  321,  322,  350,  321,  322,  351,  321,  322,  352,  321,  322,  353,
	  321,  322,  354,  321,  322,  354,  321,  322,  355,  321,  322,  356,
	  321,  322,  347,  354,  321,  322,  357,  321,  322,  358,  321,  322,
	  359,  321,  322,  360,  321,  322,  361,  321,  322,  347,  321,  322,
	  362,  321,  322,  363,  321,  322,  364,  321,  322,  365,  321,  322,
	  366,  321,  322,  367,  321,  322,  368,  321,  322,  369,  321,  322,
	  370,  321,  322,  371,  321,  322,  372,  321,  322,  373,  321,  322,
	  374,  321,  322,  361,  321,  322,  375,  366,  321,  322,  353,  321,
	  376,  377,  376,    0,  380,  379,  381,  382,  379,  378,    0,  384,
	  385,  383,    0,  384,  383,  380,  386,  384,  385,  386,  383,  380,
	  387,  388,  389,  390,  391,  392,  393,  394,  395,  396,  397,  398,
	  399,  400,  387,    0,  401,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   44,   62,
	   65,  119,  180,  247,  303,    3,    6,    7,    8,    9,    8,    8,
	    9,    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,
	   14,    4,   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,
	   24,   25,   26,   27,   28,   29,   30,  309,   32,   33,    4,   16,
	   33,    4,   16,   35,   36,    4,   35,   34,   36,   38,   39,   40,
	   41,   42,   43,   31,   45,   46,   47,   48,   49,   50,   51,   52,
	   53,   54,   53,   54,   54,    4,   55,   56,   57,   58,   59,   60,
	   61,    4,    4,    5,   15,   17,   31,   34,   37,   44,   62,   65,
	  119,  180,  247,  303,   63,   64,   66,   67,   68,   69,   70,   71,
	   72,   73,   72,   73,   73,    4,   74,   88,   96,  101,  105,  115,
	   75,   76,   77,   78,   79,   80,   81,   82,   83,   84,   85,   86,
	   87,    4,   89,   90,   91,   92,   93,   94,   95,   61,   97,   98,
	   99,  100,  102,  103,  104,  105,  106,  107,  108,  109,  110,  111,
	  112,  113,  114,  116,  117,  118,  120,  121,  122,  123,  124,  125,
	  126,  127,  128,  129,  130,  131,  132,  133,  134,  135,  136,  135,
	  136,  136,    4,  137,  151,  152,  159,  160,  163,  169,  138,  139,
	  140,  141,  142,  143,  144,  145,  146,  147,  148,  149,  150,    4,
	   61,  153,  154,  155,  156,  157,  158,  151,  161,  162,  164,  165,
	  166,  167,  168,  170,  171,  172,  173,  174,  175,  176,  177,  178,
	  179,  181,  182,  183,  184,  185,  186,  187,  188,  189,  190,  191,
	  192,  193,  194,  193,  194,  194,    4,  195,  209,  210,  217,  218,
	  221,  227,  241,  243,  196,  197,  198,  199,  200,  201,  202,  203,
	  204,  205,  206,  207,  208,    4,   61,  211,  212,  213,  214,  215,
	  216,  209,  219,  220,  222,  223,  224,  225,  226,  228,  229,  230,
	  231,  232,  233,  234,  235,  236,  237,  238,  239,  240,  242,  244,
	  245,  246,  248,  249,  250,  251,  252,  253,  254,  253,  254,  254,
	    4,  255,  269,  270,  277,  278,  281,  287,  301,  256,  257,  258,
	  259,  260,  261,  262,  263,  264,  265,  266,  267,  268,    4,   61,
	  271,  272,  273,  274,  275,  276,  269,  279,  280,  282,  283,  284,
	  285,  286,  288,  289,  290,  291,  292,  293,  294,  295,  296,  297,
	  298,  299,  300,  302,  303,  304,  305,  307,  308,  306,  304,  305,
	  306,  304,  307,  308,    5,   15,   17,   31,   34,   37,   44,   62,
	   65,  119,  180,  247,  303,    0
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
	   57,  144,    0,   54,    0,   81,   84,    0,    0,    0,    0,    0,
	   21,   31,  130,   60,   57,   31,   63,   57,   63,   63,   63,   63,
	   63,   63,   63,   66,    0,    0,    0,    0,    0,    0,    0,    0,
	   57,  144,    0,   54,    0,   69,   33,   84,   84,   84,   84,   84,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   13,    0,    0,    0,    0,    0,    0,    0,   13,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,
	   54,    0,   78,   33,   84,   84,   84,   84,   84,   84,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   19,
	   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   57,  144,    0,   54,    0,   75,   33,   84,   84,   84,   84,
	   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   17,   17,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,
	   72,   33,   84,   84,   84,   84,   84,   84,   84,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   15,   15,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   37,   37,   54,   37,   87,    0,
	    0,   39,    0,    0,   93,   90,   41,   96,   90,   96,   96,   96,
	   96,   96,   96,   96,   99,    0
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
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 309;

static final int lexer_en_main = 1;


// line 159 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"

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

    
// line 501 "java/src/main/java/gherkin/lexer/Et.java"
	{
	cs = lexer_start;
	}

// line 185 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
    
// line 508 "java/src/main/java/gherkin/lexer/Et.java"
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
// line 16 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replaceAll("\\\\\"\\\\\"\\\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
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
// line 136 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 769 "java/src/main/java/gherkin/lexer/Et.java"
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
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 801 "java/src/main/java/gherkin/lexer/Et.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/et.java.rl"
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
