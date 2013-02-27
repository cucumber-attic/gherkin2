
// line 1 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class Et implements Lexer {
  
// line 150 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"


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
	   58,   63,   68,   72,   76,   78,   79,   80,   81,   82,   83,   84,
	   85,   86,   87,   88,   89,   90,   91,   92,   93,   94,   96,   98,
	  103,  110,  115,  116,  117,  118,  119,  120,  121,  122,  124,  125,
	  126,  127,  128,  129,  130,  131,  132,  133,  140,  142,  144,  146,
	  148,  150,  152,  169,  170,  171,  173,  174,  175,  176,  177,  178,
	  179,  180,  181,  193,  195,  197,  199,  201,  203,  205,  207,  209,
	  211,  213,  215,  217,  219,  221,  223,  225,  227,  229,  231,  233,
	  235,  237,  239,  241,  243,  245,  247,  249,  251,  253,  255,  257,
	  259,  261,  263,  265,  267,  269,  271,  273,  275,  277,  279,  281,
	  283,  284,  285,  286,  287,  288,  289,  290,  291,  292,  293,  294,
	  295,  296,  297,  298,  299,  300,  313,  315,  317,  319,  321,  323,
	  325,  327,  329,  331,  333,  335,  337,  339,  341,  343,  345,  347,
	  349,  351,  353,  355,  357,  359,  361,  363,  366,  368,  370,  372,
	  374,  376,  378,  381,  383,  385,  387,  389,  391,  393,  395,  397,
	  399,  401,  403,  404,  405,  406,  407,  408,  409,  410,  411,  412,
	  413,  414,  415,  416,  431,  433,  435,  437,  439,  441,  443,  445,
	  447,  449,  451,  453,  455,  457,  459,  461,  463,  465,  467,  469,
	  471,  473,  475,  477,  479,  481,  484,  486,  488,  490,  492,  494,
	  496,  498,  500,  502,  504,  506,  508,  510,  512,  514,  516,  518,
	  520,  522,  524,  527,  529,  531,  533,  535,  537,  538,  539,  540,
	  541,  542,  543,  544,  558,  560,  562,  564,  566,  568,  570,  572,
	  574,  576,  578,  580,  582,  584,  586,  588,  590,  592,  594,  596,
	  598,  600,  602,  604,  606,  608,  611,  613,  615,  617,  619,  621,
	  623,  625,  627,  629,  631,  633,  635,  637,  639,  641,  643,  645,
	  647,  649,  651,  654,  656,  660,  666,  669,  671,  677,  694
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
	   34,    9,   13,   10,   32,   34,    9,   13,   10,   32,    9,   13,
	   10,   32,    9,   13,   10,   13,   10,   95,   70,   69,   65,   84,
	   85,   82,   69,   95,   69,   78,   68,   95,   37,   32,   10,   13,
	   10,   13,   13,   32,   64,    9,   10,    9,   10,   13,   32,   64,
	   11,   12,   10,   32,   64,    9,   13,  101,  108,  100,   97,  100,
	  101,  115,   97,  117,  104,  116,  117,  109,  105,  100,   58,   10,
	   10,   10,   32,   35,   79,  124,    9,   13,   10,  109,   10,   97,
	   10,  100,   10,  117,   10,  115,   10,   58,   10,   32,   34,   35,
	   37,   42,   64,   69,   74,   75,   79,   82,   83,   84,  124,    9,
	   13,  117,  105,   32,  100,  109,   97,  100,  117,  115,   58,   10,
	   10,   10,   32,   35,   37,   64,   74,   79,   82,   83,   84,    9,
	   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,
	   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,
	   68,   10,   95,   10,   37,   10,  117,   10,  104,   10,  116,   10,
	  117,   10,  109,   10,  105,   10,  100,   10,   58,   10,  109,   10,
	   97,   10,  100,   10,  117,   10,  115,   10,   97,   10,   97,   10,
	  109,   10,  115,   10,  116,   10,  115,   10,  101,   10,  110,   10,
	   97,   10,   97,   10,  114,   10,  105,   10,  117,   10,  109,   10,
	   97,   10,  117,   10,  115,   10,  116,   97,   97,  109,  115,  116,
	  115,  101,  110,   97,   97,  114,  105,  117,  109,   58,   10,   10,
	   10,   32,   35,   37,   42,   64,   69,   74,   75,   79,   83,    9,
	   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,
	   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,
	   68,   10,   95,   10,   37,   10,   32,   10,  101,   10,  108,   10,
	  100,   10,   97,   10,  100,   10,  101,   10,  115,   10,   97,   10,
	  117,   10,  105,   10,   32,  100,   10,  109,   10,   97,   10,  100,
	   10,  117,   10,  115,   10,   58,   10,  105,  116,   10,  105,   10,
	  115,   10,  101,   10,  110,   10,   97,   10,   97,   10,  114,   10,
	  105,   10,  117,   10,  109,  105,  116,  105,  115,  101,  110,   97,
	   97,  114,  105,  117,  109,   58,   10,   10,   10,   32,   35,   37,
	   42,   64,   69,   74,   75,   79,   82,   83,   84,    9,   13,   10,
	   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,   85,   10,
	   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,   68,   10,
	   95,   10,   37,   10,   32,   10,  101,   10,  108,   10,  100,   10,
	   97,   10,  100,   10,  101,   10,  115,   10,   97,   10,  117,   10,
	  105,   10,   32,  100,   10,  109,   10,   97,   10,  100,   10,  117,
	   10,  115,   10,   58,   10,   97,   10,   97,   10,  109,   10,  115,
	   10,  116,   10,  115,   10,  101,   10,  110,   10,   97,   10,   97,
	   10,  114,   10,  105,   10,  117,   10,  109,   10,  105,  116,   10,
	  105,   10,   97,   10,  117,   10,  115,   10,  116,   97,  117,  115,
	  116,   58,   10,   10,   10,   32,   35,   37,   42,   64,   69,   74,
	   75,   79,   82,   83,    9,   13,   10,   95,   10,   70,   10,   69,
	   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,
	   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,   32,
	   10,  101,   10,  108,   10,  100,   10,   97,   10,  100,   10,  101,
	   10,  115,   10,   97,   10,  117,   10,  105,   10,   32,  100,   10,
	  109,   10,   97,   10,  100,   10,  117,   10,  115,   10,   58,   10,
	   97,   10,   97,   10,  109,   10,  115,   10,  116,   10,  115,   10,
	  101,   10,  110,   10,   97,   10,   97,   10,  114,   10,  105,   10,
	  117,   10,  109,   10,  105,  116,   10,  105,   32,  124,    9,   13,
	   10,   32,   92,  124,    9,   13,   10,   92,  124,   10,   92,   10,
	   32,   92,  124,    9,   13,   10,   32,   34,   35,   37,   42,   64,
	   69,   74,   75,   79,   82,   83,   84,  124,    9,   13,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   16,    1,    1,   15,    1,    1,    2,    2,    3,    3,    3,
	    3,    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,
	    5,    3,    1,    1,    1,    1,    1,    1,    1,    2,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    5,    2,    2,    2,    2,
	    2,    2,   15,    1,    1,    2,    1,    1,    1,    1,    1,    1,
	    1,    1,   10,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,   11,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,
	    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,   13,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    3,    2,    2,    2,    2,    2,    1,    1,    1,    1,
	    1,    1,    1,   12,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    3,    2,    2,    4,    3,    2,    4,   15,    0
	};
}

private static final byte _lexer_single_lengths[] = init__lexer_single_lengths_0();


private static byte[] init__lexer_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    0,    1,    0,    0,    0,    0,    1,    1,    1,
	    1,    1,    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
	    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    1,    0,    0,    1,    1,    0
	};
}

private static final byte _lexer_range_lengths[] = init__lexer_range_lengths_0();


private static short[] init__lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,   18,   20,   22,   39,   41,   43,   46,   49,   54,   59,
	   64,   69,   74,   78,   82,   85,   87,   89,   91,   93,   95,   97,
	   99,  101,  103,  105,  107,  109,  111,  113,  115,  117,  120,  123,
	  128,  135,  140,  142,  144,  146,  148,  150,  152,  154,  157,  159,
	  161,  163,  165,  167,  169,  171,  173,  175,  182,  185,  188,  191,
	  194,  197,  200,  217,  219,  221,  224,  226,  228,  230,  232,  234,
	  236,  238,  240,  252,  255,  258,  261,  264,  267,  270,  273,  276,
	  279,  282,  285,  288,  291,  294,  297,  300,  303,  306,  309,  312,
	  315,  318,  321,  324,  327,  330,  333,  336,  339,  342,  345,  348,
	  351,  354,  357,  360,  363,  366,  369,  372,  375,  378,  381,  384,
	  387,  389,  391,  393,  395,  397,  399,  401,  403,  405,  407,  409,
	  411,  413,  415,  417,  419,  421,  434,  437,  440,  443,  446,  449,
	  452,  455,  458,  461,  464,  467,  470,  473,  476,  479,  482,  485,
	  488,  491,  494,  497,  500,  503,  506,  509,  513,  516,  519,  522,
	  525,  528,  531,  535,  538,  541,  544,  547,  550,  553,  556,  559,
	  562,  565,  568,  570,  572,  574,  576,  578,  580,  582,  584,  586,
	  588,  590,  592,  594,  609,  612,  615,  618,  621,  624,  627,  630,
	  633,  636,  639,  642,  645,  648,  651,  654,  657,  660,  663,  666,
	  669,  672,  675,  678,  681,  684,  688,  691,  694,  697,  700,  703,
	  706,  709,  712,  715,  718,  721,  724,  727,  730,  733,  736,  739,
	  742,  745,  748,  752,  755,  758,  761,  764,  767,  769,  771,  773,
	  775,  777,  779,  781,  795,  798,  801,  804,  807,  810,  813,  816,
	  819,  822,  825,  828,  831,  834,  837,  840,  843,  846,  849,  852,
	  855,  858,  861,  864,  867,  870,  874,  877,  880,  883,  886,  889,
	  892,  895,  898,  901,  904,  907,  910,  913,  916,  919,  922,  925,
	  928,  931,  934,  938,  941,  945,  951,  955,  958,  964,  981
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
	   31,   34,   31,   30,   32,   31,   35,   31,   30,   37,   36,   38,
	   36,    0,    3,   39,   39,    0,   37,   36,   36,    0,   41,   42,
	   40,    3,    0,   43,    0,   44,    0,   45,    0,   46,    0,   47,
	    0,   48,    0,   49,    0,   50,    0,   51,    0,   52,    0,   53,
	    0,   54,    0,   55,    0,   56,    0,   57,    0,   59,   60,   58,
	   62,   63,   61,    0,    0,    0,    0,   64,   65,   66,   65,   65,
	   68,   67,   64,    3,   69,    8,   69,    0,   70,    0,   71,    0,
	   72,    0,   73,    0,   74,    0,   75,    0,   76,    0,   76,   77,
	    0,   78,    0,   79,    0,   80,    0,   81,    0,   82,    0,   83,
	    0,   84,    0,   86,   85,   88,   87,   88,   89,   90,   91,   90,
	   89,   87,   88,   92,   87,   88,   93,   87,   88,   94,   87,   88,
	   95,   87,   88,   96,   87,   88,   97,   87,   99,   98,  100,  101,
	  102,  103,  104,  105,  106,  107,  108,  109,  110,  111,  112,   98,
	    0,  113,    0,  114,    0,   57,   76,    0,  115,    0,  116,    0,
	  117,    0,  118,    0,  119,    0,  120,    0,  122,  121,  124,  123,
	  124,  125,  126,  127,  126,  128,  129,  130,  131,  132,  125,  123,
	  124,  133,  123,  124,  134,  123,  124,  135,  123,  124,  136,  123,
	  124,  137,  123,  124,  138,  123,  124,  139,  123,  124,  140,  123,
	  124,  141,  123,  124,  142,  123,  124,  143,  123,  124,  144,  123,
	  124,  145,  123,  124,  146,  123,  124,  147,  123,  124,  148,  123,
	  124,  149,  123,  124,  150,  123,  124,  151,  123,  124,  152,  123,
	  124,  153,  123,  124,  154,  123,  124,  155,  123,  124,  156,  123,
	  124,  157,  123,  124,  158,  123,  124,  153,  123,  124,  159,  123,
	  124,  160,  123,  124,  161,  123,  124,  162,  123,  124,  163,  123,
	  124,  164,  123,  124,  165,  123,  124,  166,  123,  124,  167,  123,
	  124,  168,  123,  124,  169,  123,  124,  170,  123,  124,  171,  123,
	  124,  153,  123,  124,  172,  123,  124,  173,  123,  124,  174,  123,
	  124,  153,  123,  175,    0,  176,    0,  177,    0,  178,    0,  179,
	    0,  180,    0,  181,    0,  182,    0,  183,    0,  184,    0,  185,
	    0,  186,    0,  187,    0,  188,    0,  189,    0,  191,  190,  193,
	  192,  193,  194,  195,  196,  197,  195,  198,  199,  200,  201,  202,
	  194,  192,  193,  203,  192,  193,  204,  192,  193,  205,  192,  193,
	  206,  192,  193,  207,  192,  193,  208,  192,  193,  209,  192,  193,
	  210,  192,  193,  211,  192,  193,  212,  192,  193,  213,  192,  193,
	  214,  192,  193,  215,  192,  193,  216,  192,  193,  217,  192,  193,
	  218,  192,  193,  219,  192,  193,  220,  192,  193,  221,  192,  193,
	  222,  192,  193,  223,  192,  193,  224,  192,  193,  224,  192,  193,
	  225,  192,  193,  226,  192,  193,  217,  224,  192,  193,  227,  192,
	  193,  228,  192,  193,  229,  192,  193,  230,  192,  193,  231,  192,
	  193,  217,  192,  193,  232,  233,  192,  193,  223,  192,  193,  234,
	  192,  193,  235,  192,  193,  236,  192,  193,  237,  192,  193,  238,
	  192,  193,  239,  192,  193,  240,  192,  193,  241,  192,  193,  231,
	  192,  242,  243,    0,   75,    0,  244,    0,  245,    0,  246,    0,
	  247,    0,  248,    0,  249,    0,  250,    0,  251,    0,  252,    0,
	  253,    0,  255,  254,  257,  256,  257,  258,  259,  260,  261,  259,
	  262,  263,  264,  265,  266,  267,  268,  258,  256,  257,  269,  256,
	  257,  270,  256,  257,  271,  256,  257,  272,  256,  257,  273,  256,
	  257,  274,  256,  257,  275,  256,  257,  276,  256,  257,  277,  256,
	  257,  278,  256,  257,  279,  256,  257,  280,  256,  257,  281,  256,
	  257,  282,  256,  257,  283,  256,  257,  284,  256,  257,  285,  256,
	  257,  286,  256,  257,  287,  256,  257,  288,  256,  257,  289,  256,
	  257,  290,  256,  257,  290,  256,  257,  291,  256,  257,  292,  256,
	  257,  283,  290,  256,  257,  293,  256,  257,  294,  256,  257,  295,
	  256,  257,  296,  256,  257,  297,  256,  257,  283,  256,  257,  298,
	  256,  257,  299,  256,  257,  300,  256,  257,  301,  256,  257,  302,
	  256,  257,  303,  256,  257,  304,  256,  257,  305,  256,  257,  306,
	  256,  257,  307,  256,  257,  308,  256,  257,  309,  256,  257,  310,
	  256,  257,  297,  256,  257,  311,  302,  256,  257,  289,  256,  257,
	  312,  256,  257,  313,  256,  257,  314,  256,  257,  297,  256,  315,
	    0,  316,    0,  317,    0,  318,    0,  319,    0,  321,  320,  323,
	  322,  323,  324,  325,  326,  327,  325,  328,  329,  330,  331,  332,
	  333,  324,  322,  323,  334,  322,  323,  335,  322,  323,  336,  322,
	  323,  337,  322,  323,  338,  322,  323,  339,  322,  323,  340,  322,
	  323,  341,  322,  323,  342,  322,  323,  343,  322,  323,  344,  322,
	  323,  345,  322,  323,  346,  322,  323,  347,  322,  323,  348,  322,
	  323,  349,  322,  323,  350,  322,  323,  351,  322,  323,  352,  322,
	  323,  353,  322,  323,  354,  322,  323,  355,  322,  323,  355,  322,
	  323,  356,  322,  323,  357,  322,  323,  348,  355,  322,  323,  358,
	  322,  323,  359,  322,  323,  360,  322,  323,  361,  322,  323,  362,
	  322,  323,  348,  322,  323,  363,  322,  323,  364,  322,  323,  365,
	  322,  323,  366,  322,  323,  367,  322,  323,  368,  322,  323,  369,
	  322,  323,  370,  322,  323,  371,  322,  323,  372,  322,  323,  373,
	  322,  323,  374,  322,  323,  375,  322,  323,  362,  322,  323,  376,
	  367,  322,  323,  354,  322,  377,  378,  377,    0,  381,  380,  382,
	  383,  380,  379,    0,  385,  386,  384,    0,  385,  384,  381,  387,
	  385,  386,  387,  384,  381,  388,  389,  390,  391,  392,  393,  394,
	  395,  396,  397,  398,  399,  400,  401,  388,    0,  402,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   16,   18,   32,   35,   38,   45,   63,
	   66,  120,  181,  248,  304,    3,    6,    7,    8,    9,    8,    8,
	    9,    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,
	   14,    4,   15,   14,   16,    4,   17,   19,   20,   21,   22,   23,
	   24,   25,   26,   27,   28,   29,   30,   31,  310,   33,   34,    4,
	   17,   34,    4,   17,   36,   37,    4,   36,   35,   37,   39,   40,
	   41,   42,   43,   44,   32,   46,   47,   48,   49,   50,   51,   52,
	   53,   54,   55,   54,   55,   55,    4,   56,   57,   58,   59,   60,
	   61,   62,    4,    4,    5,   16,   18,   32,   35,   38,   45,   63,
	   66,  120,  181,  248,  304,   64,   65,   67,   68,   69,   70,   71,
	   72,   73,   74,   73,   74,   74,    4,   75,   89,   97,  102,  106,
	  116,   76,   77,   78,   79,   80,   81,   82,   83,   84,   85,   86,
	   87,   88,    4,   90,   91,   92,   93,   94,   95,   96,   62,   98,
	   99,  100,  101,  103,  104,  105,  106,  107,  108,  109,  110,  111,
	  112,  113,  114,  115,  117,  118,  119,  121,  122,  123,  124,  125,
	  126,  127,  128,  129,  130,  131,  132,  133,  134,  135,  136,  137,
	  136,  137,  137,    4,  138,  152,  153,  160,  161,  164,  170,  139,
	  140,  141,  142,  143,  144,  145,  146,  147,  148,  149,  150,  151,
	    4,   62,  154,  155,  156,  157,  158,  159,  152,  162,  163,  165,
	  166,  167,  168,  169,  171,  172,  173,  174,  175,  176,  177,  178,
	  179,  180,  182,  183,  184,  185,  186,  187,  188,  189,  190,  191,
	  192,  193,  194,  195,  194,  195,  195,    4,  196,  210,  211,  218,
	  219,  222,  228,  242,  244,  197,  198,  199,  200,  201,  202,  203,
	  204,  205,  206,  207,  208,  209,    4,   62,  212,  213,  214,  215,
	  216,  217,  210,  220,  221,  223,  224,  225,  226,  227,  229,  230,
	  231,  232,  233,  234,  235,  236,  237,  238,  239,  240,  241,  243,
	  245,  246,  247,  249,  250,  251,  252,  253,  254,  255,  254,  255,
	  255,    4,  256,  270,  271,  278,  279,  282,  288,  302,  257,  258,
	  259,  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,    4,
	   62,  272,  273,  274,  275,  276,  277,  270,  280,  281,  283,  284,
	  285,  286,  287,  289,  290,  291,  292,  293,  294,  295,  296,  297,
	  298,  299,  300,  301,  303,  304,  305,  306,  308,  309,  307,  305,
	  306,  307,  305,  308,  309,    5,   16,   18,   32,   35,   38,   45,
	   63,   66,  120,  181,  248,  304,    0
	};
}

private static final short _lexer_trans_targs[] = init__lexer_trans_targs_0();


private static short[] init__lexer_trans_actions_0()
{
	return new short [] {
	   43,    0,    0,   54,    3,    1,    0,   29,    1,   29,   29,   29,
	   29,   29,   29,   29,   35,    0,    0,    0,    7,  139,   48,    0,
	  102,    9,    5,   45,  134,   45,    0,   33,  122,   33,   33,    0,
	   11,  106,    0,    0,    0,  114,   25,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,  149,
	  126,    0,  110,   23,    0,   27,  118,   27,   51,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   57,  144,    0,   54,    0,   81,   84,    0,    0,    0,    0,
	    0,   21,   31,  130,   60,   57,   31,   63,   57,   63,   63,   63,
	   63,   63,   63,   63,   66,    0,    0,    0,    0,    0,    0,    0,
	    0,   57,  144,    0,   54,    0,   69,   33,   84,   84,   84,   84,
	   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   13,    0,    0,    0,    0,    0,    0,    0,   13,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,  144,
	    0,   54,    0,   78,   33,   84,   84,   84,   84,   84,   84,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	   19,   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   75,   33,   84,   84,   84,
	   84,   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   17,   17,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,
	    0,   72,   33,   84,   84,   84,   84,   84,   84,   84,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   15,
	   15,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,   37,   37,   54,   37,   87,
	    0,    0,   39,    0,    0,   93,   90,   41,   96,   90,   96,   96,
	   96,   96,   96,   96,   96,   99,    0
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
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 310;

static final int lexer_en_main = 1;


// line 159 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"

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

// line 185 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
    
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
// line 16 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replace("\\\"\\\"\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
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
// line 136 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
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
// line 140 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
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

// line 186 "/home/son/work/github/os97673/gherkin/tasks/../ragel/i18n/et.java.rl"
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
