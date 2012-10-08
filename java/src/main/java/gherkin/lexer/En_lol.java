
// line 1 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class En_lol implements Lexer {
  
// line 150 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"


  private final Listener listener;

  public En_lol(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/En_lol.java"
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
	  107,  112,  113,  115,  116,  117,  118,  133,  135,  137,  139,  141,
	  143,  145,  147,  149,  151,  153,  155,  157,  159,  161,  163,  181,
	  182,  183,  184,  185,  186,  187,  188,  189,  190,  191,  198,  200,
	  202,  204,  206,  208,  210,  211,  212,  213,  214,  215,  216,  217,
	  218,  219,  220,  221,  222,  223,  225,  226,  227,  228,  229,  230,
	  231,  232,  233,  248,  250,  252,  254,  256,  258,  260,  262,  264,
	  266,  268,  270,  272,  274,  276,  278,  280,  282,  284,  286,  288,
	  290,  292,  294,  296,  298,  300,  302,  304,  306,  308,  310,  312,
	  314,  316,  318,  320,  322,  324,  325,  326,  341,  343,  345,  347,
	  349,  351,  353,  355,  357,  359,  361,  363,  365,  367,  369,  371,
	  373,  376,  378,  380,  382,  384,  386,  388,  390,  392,  394,  396,
	  398,  400,  402,  404,  406,  408,  411,  413,  415,  417,  419,  421,
	  423,  425,  427,  429,  431,  432,  433,  434,  435,  436,  437,  438,
	  439,  450,  452,  454,  456,  458,  460,  462,  464,  466,  468,  470,
	  472,  474,  476,  478,  480,  482,  484,  486,  488,  490,  492,  494,
	  496,  498,  500,  502,  504,  507,  509,  511,  513,  515,  517,  519,
	  521,  523,  525,  527,  531,  537,  540,  542,  548,  566,  568,  570,
	  572,  574,  576,  578,  580,  582,  584,  586,  588,  590,  592,  594,
	  596,  598,  600,  603,  605,  607,  609,  611,  613,  615,  617,  619,
	  621,  623,  625,  626
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   65,   66,   68,   69,
	   73,   77,   79,   87,  124,    9,   13,  -69,  -65,   10,   32,   34,
	   35,   37,   42,   64,   65,   66,   68,   69,   73,   77,   79,   87,
	  124,    9,   13,   34,   34,   10,   13,   10,   13,   10,   32,   34,
	    9,   13,   10,   32,   34,    9,   13,   10,   32,   34,    9,   13,
	   10,   32,   34,    9,   13,   10,   32,    9,   13,   10,   32,    9,
	   13,   10,   13,   10,   95,   70,   69,   65,   84,   85,   82,   69,
	   95,   69,   78,   68,   95,   37,   32,   10,   13,   10,   13,   13,
	   32,   64,    9,   10,    9,   10,   13,   32,   64,   11,   12,   10,
	   32,   64,    9,   13,   78,   52,   85,   58,   10,   10,   10,   32,
	   35,   37,   42,   64,   65,   66,   68,   73,   77,   79,   87,    9,
	   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,
	   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,
	   68,   10,   95,   10,   37,   10,   32,   10,   32,   34,   35,   37,
	   42,   64,   65,   66,   68,   69,   73,   77,   79,   87,  124,    9,
	   13,   69,   88,   65,   77,   80,   76,   90,   58,   10,   10,   10,
	   32,   35,   79,  124,    9,   13,   10,   72,   10,   32,   10,   72,
	   10,   65,   10,   73,   10,   58,   32,   67,   65,   78,   32,   72,
	   65,   90,   73,   83,   72,   85,   78,   32,   58,   83,   82,   83,
	   76,   89,   58,   10,   10,   10,   32,   35,   37,   42,   64,   65,
	   66,   68,   73,   77,   79,   87,    9,   13,   10,   95,   10,   70,
	   10,   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,
	   10,   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,
	   10,   32,   10,   78,   10,   85,   10,   84,   10,   69,   10,   32,
	   10,   67,   10,   65,   10,   78,   10,   32,   10,   72,   10,   65,
	   10,   90,   10,   73,   10,   83,   10,   72,   10,   85,   10,   78,
	   10,   58,   10,   72,   10,   32,   10,   72,   10,   65,   10,   73,
	   10,   10,   10,   32,   35,   37,   42,   64,   65,   66,   68,   73,
	   77,   79,   87,    9,   13,   10,   95,   10,   70,   10,   69,   10,
	   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,   10,
	   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,   32,   10,
	   78,   10,   52,   85,   10,   58,   10,   84,   10,   69,   10,   32,
	   10,   67,   10,   65,   10,   78,   10,   32,   10,   72,   10,   65,
	   10,   90,   10,   73,   10,   83,   10,   72,   10,   85,   10,   78,
	   10,   32,   58,   10,   83,   10,   82,   10,   83,   10,   76,   10,
	   89,   10,   72,   10,   32,   10,   72,   10,   65,   10,   73,   72,
	   32,   72,   65,   73,   58,   10,   10,   10,   32,   35,   37,   64,
	   66,   69,   77,   79,    9,   13,   10,   95,   10,   70,   10,   69,
	   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,
	   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,   52,
	   10,   58,   10,   88,   10,   65,   10,   77,   10,   80,   10,   76,
	   10,   90,   10,   73,   10,   83,   10,   72,   10,   85,   10,   78,
	   10,   32,   58,   10,   83,   10,   82,   10,   83,   10,   76,   10,
	   89,   10,   72,   10,   32,   10,   72,   10,   65,   10,   73,   32,
	  124,    9,   13,   10,   32,   92,  124,    9,   13,   10,   92,  124,
	   10,   92,   10,   32,   92,  124,    9,   13,   10,   32,   34,   35,
	   37,   42,   64,   65,   66,   68,   69,   73,   77,   79,   87,  124,
	    9,   13,   10,   78,   10,   85,   10,   84,   10,   69,   10,   32,
	   10,   67,   10,   65,   10,   78,   10,   32,   10,   72,   10,   65,
	   10,   90,   10,   73,   10,   83,   10,   72,   10,   85,   10,   78,
	   10,   32,   58,   10,   83,   10,   82,   10,   83,   10,   76,   10,
	   89,   10,   58,   10,   72,   10,   32,   10,   72,   10,   65,   10,
	   73,   84,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   17,    1,    1,   16,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    1,    2,    1,    1,    1,   13,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,   16,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    5,    2,    2,
	    2,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    2,    1,    1,    1,    1,    1,    1,
	    1,    1,   13,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    1,    1,   13,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    9,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    4,    3,    2,    4,   16,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,
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
	    1,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
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
	  132,  137,  139,  142,  144,  146,  148,  163,  166,  169,  172,  175,
	  178,  181,  184,  187,  190,  193,  196,  199,  202,  205,  208,  226,
	  228,  230,  232,  234,  236,  238,  240,  242,  244,  246,  253,  256,
	  259,  262,  265,  268,  271,  273,  275,  277,  279,  281,  283,  285,
	  287,  289,  291,  293,  295,  297,  300,  302,  304,  306,  308,  310,
	  312,  314,  316,  331,  334,  337,  340,  343,  346,  349,  352,  355,
	  358,  361,  364,  367,  370,  373,  376,  379,  382,  385,  388,  391,
	  394,  397,  400,  403,  406,  409,  412,  415,  418,  421,  424,  427,
	  430,  433,  436,  439,  442,  445,  447,  449,  464,  467,  470,  473,
	  476,  479,  482,  485,  488,  491,  494,  497,  500,  503,  506,  509,
	  512,  516,  519,  522,  525,  528,  531,  534,  537,  540,  543,  546,
	  549,  552,  555,  558,  561,  564,  568,  571,  574,  577,  580,  583,
	  586,  589,  592,  595,  598,  600,  602,  604,  606,  608,  610,  612,
	  614,  625,  628,  631,  634,  637,  640,  643,  646,  649,  652,  655,
	  658,  661,  664,  667,  670,  673,  676,  679,  682,  685,  688,  691,
	  694,  697,  700,  703,  706,  710,  713,  716,  719,  722,  725,  728,
	  731,  734,  737,  740,  744,  750,  754,  757,  763,  781,  784,  787,
	  790,  793,  796,  799,  802,  805,  808,  811,  814,  817,  820,  823,
	  826,  829,  832,  836,  839,  842,  845,  848,  851,  854,  857,  860,
	  863,  866,  869,  871
	};
}

private static final short _lexer_index_offsets[] = init__lexer_index_offsets_0();


private static short[] init__lexer_indicies_0()
{
	return new short [] {
	    1,    3,    2,    4,    5,    6,    7,    8,    9,   10,   11,   12,
	   13,   14,   15,   11,   16,    2,    0,   17,    0,    2,    0,    3,
	    2,    4,    5,    6,    7,    8,    9,   10,   11,   12,   13,   14,
	   15,   11,   16,    2,    0,   18,    0,   19,    0,   21,   22,   20,
	   24,   25,   23,   28,   27,   29,   27,   26,   32,   31,   33,   31,
	   30,   32,   31,   34,   31,   30,   32,   31,   35,   31,   30,   37,
	   36,   36,    0,    3,   38,   38,    0,   40,   41,   39,    3,    0,
	   42,    0,   43,    0,   44,    0,   45,    0,   46,    0,   47,    0,
	   48,    0,   49,    0,   50,    0,   51,    0,   52,    0,   53,    0,
	   54,    0,   55,    0,   56,    0,   58,   59,   57,   61,   62,   60,
	    0,    0,    0,    0,   63,   64,   65,   64,   64,   67,   66,   63,
	    3,   68,    8,   68,    0,   69,    0,   70,   71,    0,   72,    0,
	   74,   73,   76,   75,   76,   77,   78,   79,   80,   78,   81,   82,
	   83,   84,   85,   86,   83,   77,   75,   76,   87,   75,   76,   88,
	   75,   76,   89,   75,   76,   90,   75,   76,   91,   75,   76,   92,
	   75,   76,   93,   75,   76,   94,   75,   76,   95,   75,   76,   96,
	   75,   76,   97,   75,   76,   98,   75,   76,   99,   75,   76,  100,
	   75,   76,  101,   75,  103,  102,  104,  105,  106,  107,  108,  109,
	  110,  111,  112,  113,  114,  115,  111,  116,  102,    0,  117,    0,
	  118,    0,  119,    0,  120,    0,  121,    0,  122,    0,  123,    0,
	  124,    0,  126,  125,  128,  127,  128,  129,  130,  131,  130,  129,
	  127,  128,  132,  127,  128,  133,  127,  128,  134,  127,  128,  135,
	  127,  128,  136,  127,  128,  137,  127,  138,    0,  139,    0,  140,
	    0,  141,    0,  142,    0,  143,    0,  144,    0,   69,    0,  145,
	    0,  146,    0,  147,    0,  148,    0,  149,    0,  150,  151,    0,
	  152,    0,  153,    0,  154,    0,  155,    0,  156,    0,  157,    0,
	  159,  158,  161,  160,  161,  162,  163,  164,  165,  163,  166,  167,
	  168,  169,  170,  171,  168,  162,  160,  161,  172,  160,  161,  173,
	  160,  161,  174,  160,  161,  175,  160,  161,  176,  160,  161,  177,
	  160,  161,  178,  160,  161,  179,  160,  161,  180,  160,  161,  181,
	  160,  161,  182,  160,  161,  183,  160,  161,  184,  160,  161,  185,
	  160,  161,  186,  160,  161,  187,  160,  161,  188,  160,  161,  187,
	  160,  161,  189,  160,  161,  190,  160,  161,  191,  160,  161,  192,
	  160,  161,  193,  160,  161,  194,  160,  161,  195,  160,  161,  196,
	  160,  161,  187,  160,  161,  197,  160,  161,  198,  160,  161,  199,
	  160,  161,  200,  160,  161,  201,  160,  161,  186,  160,  161,  202,
	  160,  161,  203,  160,  161,  204,  160,  161,  205,  160,  161,  201,
	  160,  207,  206,  209,  208,  209,  210,  211,  212,  213,  211,  214,
	  215,  216,  217,  218,  219,  216,  210,  208,  209,  220,  208,  209,
	  221,  208,  209,  222,  208,  209,  223,  208,  209,  224,  208,  209,
	  225,  208,  209,  226,  208,  209,  227,  208,  209,  228,  208,  209,
	  229,  208,  209,  230,  208,  209,  231,  208,  209,  232,  208,  209,
	  233,  208,  209,  234,  208,  209,  235,  208,  209,  236,  237,  208,
	  209,  234,  208,  209,  235,  208,  209,  238,  208,  209,  239,  208,
	  209,  240,  208,  209,  241,  208,  209,  242,  208,  209,  243,  208,
	  209,  244,  208,  209,  245,  208,  209,  235,  208,  209,  246,  208,
	  209,  247,  208,  209,  248,  208,  209,  249,  208,  209,  250,  208,
	  209,  251,  234,  208,  209,  252,  208,  209,  253,  208,  209,  254,
	  208,  209,  255,  208,  209,  236,  208,  209,  256,  208,  209,  257,
	  208,  209,  258,  208,  209,  259,  208,  209,  236,  208,  260,    0,
	  261,    0,  262,    0,  263,    0,  264,    0,  265,    0,  267,  266,
	  269,  268,  269,  270,  271,  272,  271,  273,  274,  275,  276,  270,
	  268,  269,  277,  268,  269,  278,  268,  269,  279,  268,  269,  280,
	  268,  269,  281,  268,  269,  282,  268,  269,  283,  268,  269,  284,
	  268,  269,  285,  268,  269,  286,  268,  269,  287,  268,  269,  288,
	  268,  269,  289,  268,  269,  290,  268,  269,  291,  268,  269,  292,
	  268,  269,  293,  268,  269,  294,  268,  269,  295,  268,  269,  296,
	  268,  269,  297,  268,  269,  291,  268,  269,  298,  268,  269,  299,
	  268,  269,  300,  268,  269,  301,  268,  269,  302,  268,  269,  303,
	  292,  268,  269,  304,  268,  269,  305,  268,  269,  306,  268,  269,
	  307,  268,  269,  291,  268,  269,  308,  268,  269,  309,  268,  269,
	  310,  268,  269,  311,  268,  269,  291,  268,  312,  313,  312,    0,
	  316,  315,  317,  318,  315,  314,    0,  320,  321,  319,    0,  320,
	  319,  316,  322,  320,  321,  322,  319,  316,  323,  324,  325,  326,
	  327,  328,  329,  330,  331,  332,  333,  334,  335,  331,  336,  323,
	    0,   76,  337,   75,   76,  338,   75,   76,  337,   75,   76,  339,
	   75,   76,  340,   75,   76,  341,   75,   76,  342,   75,   76,  343,
	   75,   76,  344,   75,   76,  345,   75,   76,  346,   75,   76,  337,
	   75,   76,  347,   75,   76,  348,   75,   76,  349,   75,   76,  350,
	   75,   76,  351,   75,   76,  352,  101,   75,   76,  353,   75,   76,
	  354,   75,   76,  355,   75,   76,  356,   75,   76,  357,   75,   76,
	  101,   75,   76,  358,   75,   76,  359,   75,   76,  360,   75,   76,
	  361,   75,   76,  357,   75,   69,    0,  362,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   38,   59,
	   60,   76,   84,  184,  231,    3,    6,    7,    8,    9,    8,    8,
	    9,    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,
	   14,    4,   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,
	   24,   25,   26,   27,   28,   29,   30,  267,   32,   33,    4,   16,
	   33,    4,   16,   35,   36,    4,   35,   34,   36,   31,   39,  266,
	   40,   41,   42,   41,   42,   42,    4,   43,   57,  237,  238,  240,
	  241,  249,  261,   44,   45,   46,   47,   48,   49,   50,   51,   52,
	   53,   54,   55,   56,    4,   58,    4,    4,    5,   15,   17,   31,
	   34,   37,   38,   59,   60,   76,   84,  184,  231,   37,   61,   62,
	   63,   64,   65,   66,   67,   68,   69,   68,   69,   69,    4,   70,
	   71,   72,   73,   74,   75,   58,   77,   78,   79,   80,   81,   82,
	   83,   85,   86,   87,   88,   89,   90,  137,   91,   92,   93,   94,
	   95,   96,   97,   98,   97,   98,   98,    4,   99,  113,  114,  115,
	  117,  118,  126,  132,  100,  101,  102,  103,  104,  105,  106,  107,
	  108,  109,  110,  111,  112,    4,   58,  113,  116,  114,  119,  120,
	  121,  122,  123,  124,  125,  127,  128,  129,  130,  131,  133,  134,
	  135,  136,  138,  139,  138,  139,  139,    4,  140,  154,  155,  156,
	  159,  160,  168,  179,  141,  142,  143,  144,  145,  146,  147,  148,
	  149,  150,  151,  152,  153,    4,   58,  154,  157,  158,  155,  161,
	  162,  163,  164,  165,  166,  167,  169,  170,  171,  172,  173,  174,
	  175,  176,  177,  178,  180,  181,  182,  183,  185,  186,  187,  188,
	  189,  190,  191,  192,  191,  192,  192,    4,  193,  207,  209,  215,
	  226,  194,  195,  196,  197,  198,  199,  200,  201,  202,  203,  204,
	  205,  206,    4,  208,   58,  210,  211,  212,  213,  214,  216,  217,
	  218,  219,  220,  221,  222,  223,  224,  225,  227,  228,  229,  230,
	  231,  232,  233,  235,  236,  234,  232,  233,  234,  232,  235,  236,
	    5,   15,   17,   31,   34,   37,   38,   59,   60,   76,   84,  184,
	  231,   57,  239,  237,  242,  243,  244,  245,  246,  247,  248,  250,
	  251,  252,  253,  254,  255,  256,  257,  258,  259,  260,  262,  263,
	  264,  265,    0
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
	    0,   57,  144,    0,   54,    0,   72,   33,   84,   84,   84,   84,
	   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,   15,   15,   31,  130,   60,   57,   31,   63,
	   57,   63,   63,   63,   63,   63,   63,   63,   66,    0,    0,    0,
	    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   81,   84,
	    0,    0,    0,    0,    0,   21,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   78,   33,   84,   84,   84,
	   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   19,   19,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   75,   33,   84,   84,   84,
	   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   17,   17,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   69,   33,   84,   84,   84,
	   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   13,    0,   13,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   37,   37,   54,   37,   87,    0,    0,   39,    0,    0,
	   93,   90,   41,   96,   90,   96,   96,   96,   96,   96,   96,   96,
	   99,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0
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
	   43,   43,   43,   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 267;

static final int lexer_en_main = 1;


// line 159 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"

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

    
// line 466 "java/src/main/java/gherkin/lexer/En_lol.java"
	{
	cs = lexer_start;
	}

// line 185 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
    
// line 473 "java/src/main/java/gherkin/lexer/En_lol.java"
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
// line 16 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replace("\\\"\\\"\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
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
// line 136 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 734 "java/src/main/java/gherkin/lexer/En_lol.java"
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
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 766 "java/src/main/java/gherkin/lexer/En_lol.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/en_lol.java.rl"
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
