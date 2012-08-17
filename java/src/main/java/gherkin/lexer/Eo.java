
// line 1 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class Eo implements Lexer {
  
// line 150 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"


  private final Listener listener;

  public Eo(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/Eo.java"
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
	  103,  108,  109,  111,  112,  113,  114,  115,  116,  117,  118,  119,
	  120,  121,  122,  123,  124,  125,  126,  127,  128,  135,  137,  139,
	  141,  143,  145,  147,  163,  164,  165,  166,  167,  168,  169,  181,
	  183,  185,  187,  189,  191,  193,  195,  197,  199,  201,  203,  205,
	  207,  209,  211,  213,  216,  218,  220,  222,  224,  226,  228,  231,
	  233,  235,  237,  239,  241,  243,  245,  247,  249,  251,  253,  255,
	  257,  259,  261,  263,  265,  267,  269,  271,  273,  276,  279,  281,
	  283,  285,  287,  289,  290,  291,  292,  293,  294,  295,  296,  297,
	  298,  299,  300,  301,  302,  303,  304,  305,  306,  307,  308,  309,
	  310,  311,  312,  324,  326,  328,  330,  332,  334,  336,  338,  340,
	  342,  344,  346,  348,  350,  352,  354,  356,  359,  361,  363,  365,
	  367,  369,  371,  373,  375,  378,  380,  382,  384,  386,  388,  390,
	  393,  395,  397,  399,  401,  403,  404,  405,  406,  407,  408,  409,
	  410,  411,  424,  426,  428,  430,  432,  434,  436,  438,  440,  442,
	  444,  446,  448,  450,  452,  454,  456,  459,  461,  463,  465,  467,
	  469,  471,  473,  475,  477,  479,  482,  484,  486,  488,  490,  492,
	  494,  496,  498,  500,  502,  504,  506,  508,  510,  512,  514,  516,
	  518,  520,  523,  526,  528,  530,  532,  534,  536,  537,  538,  539,
	  540,  541,  542,  543,  544,  556,  558,  560,  562,  564,  566,  568,
	  570,  572,  574,  576,  578,  580,  582,  584,  586,  588,  590,  592,
	  594,  596,  598,  600,  602,  604,  606,  608,  610,  612,  614,  616,
	  618,  620,  622,  624,  626,  628,  630,  632,  634,  636,  638,  640,
	  642,  644,  646,  648,  650,  652,  654,  658,  664,  667,  669,  675,
	  691
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   68,   69,   70,   75,
	   83,   84,  124,    9,   13,  -69,  -65,   10,   32,   34,   35,   37,
	   42,   64,   68,   69,   70,   75,   83,   84,  124,    9,   13,   34,
	   34,   10,   13,   10,   13,   10,   32,   34,    9,   13,   10,   32,
	   34,    9,   13,   10,   32,   34,    9,   13,   10,   32,   34,    9,
	   13,   10,   32,    9,   13,   10,   32,    9,   13,   10,   13,   10,
	   95,   70,   69,   65,   84,   85,   82,   69,   95,   69,   78,   68,
	   95,   37,   32,   10,   13,   10,   13,   13,   32,   64,    9,   10,
	    9,   10,   13,   32,   64,   11,   12,   10,   32,   64,    9,   13,
	  111,   32,  110,  105,  116,   97,  -60,  -75,  111,  107,  122,  101,
	  109,  112,  108,  111,  106,   58,   10,   10,   10,   32,   35,   84,
	  124,    9,   13,   10,  114,   10,   97,   10,  106,   10,  116,   10,
	  111,   10,   58,   10,   32,   34,   35,   37,   42,   64,   68,   69,
	   70,   75,   83,   84,  124,    9,   13,  111,  110,  111,   58,   10,
	   10,   10,   32,   35,   37,   42,   64,   68,   75,   83,   84,    9,
	   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,
	   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,
	   68,   10,   95,   10,   37,   10,   32,   10,  111,   10,   32,  110,
	   10,  105,   10,  116,   10,   97,  -60,   10,  -75,   10,   10,  111,
	   10,   97,  111,   10,  106,   10,  110,   10,  116,   10,  117,   10,
	  114,   10,  111,   10,   32,   10,  100,   10,  101,   10,   32,   10,
	  108,   10,   97,   10,   32,   10,  115,   10,   99,   10,  101,   10,
	  110,   10,   97,   10,  114,   10,  111,   10,   58,   10,   99,  101,
	   10,   32,  100,   10,  114,   10,   97,   10,  106,   10,  116,   97,
	  111,  106,  110,  116,  117,  114,  111,   32,  100,  101,   32,  108,
	   97,   32,  115,   99,  101,  110,   97,  114,  111,   58,   10,   10,
	   10,   32,   35,   37,   42,   64,   68,   75,   83,   84,    9,   13,
	   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,   85,
	   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,   68,
	   10,   95,   10,   37,   10,   32,   10,  111,   10,   32,  110,   10,
	  105,   10,  116,   10,   97,  -60,   10,  -75,   10,   10,  111,   10,
	   97,   10,  106,   10,   99,  101,   10,  101,   10,  110,   10,   97,
	   10,  114,   10,  111,   10,   58,   10,   32,  100,   10,  114,   10,
	   97,   10,  106,   10,  116,   99,  101,  101,  110,   97,  114,  111,
	   58,   10,   10,   10,   32,   35,   37,   42,   64,   68,   70,   75,
	   83,   84,    9,   13,   10,   95,   10,   70,   10,   69,   10,   65,
	   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,   10,   69,
	   10,   78,   10,   68,   10,   95,   10,   37,   10,   32,   10,  111,
	   10,   32,  110,   10,  105,   10,  116,   10,   97,  -60,   10,  -75,
	   10,   10,  111,   10,  111,   10,  110,   10,  111,   10,   58,   10,
	   97,  111,   10,  106,   10,  110,   10,  116,   10,  117,   10,  114,
	   10,  111,   10,   32,   10,  100,   10,  101,   10,   32,   10,  108,
	   10,   97,   10,   32,   10,  115,   10,   99,   10,  101,   10,  110,
	   10,   97,   10,  114,   10,   99,  101,   10,   32,  100,   10,  114,
	   10,   97,   10,  106,   10,  116,   32,  100,  114,   97,  106,  116,
	  111,   58,   10,   10,   10,   32,   35,   37,   64,   69,   70,   75,
	   83,   84,    9,   13,   10,   95,   10,   70,   10,   69,   10,   65,
	   10,   84,   10,   85,   10,   82,   10,   69,   10,   95,   10,   69,
	   10,   78,   10,   68,   10,   95,   10,   37,   10,  107,   10,  122,
	   10,  101,   10,  109,   10,  112,   10,  108,   10,  111,   10,  106,
	   10,   58,   10,  111,   10,  110,   10,  111,   10,  111,   10,  110,
	   10,  116,   10,  117,   10,  114,   10,  111,   10,   32,   10,  100,
	   10,  101,   10,   32,   10,  108,   10,   97,   10,   32,   10,  115,
	   10,   99,   10,  101,   10,  110,   10,   97,   10,  114,   10,  114,
	   10,   97,   10,  106,   10,  116,   32,  124,    9,   13,   10,   32,
	   92,  124,    9,   13,   10,   92,  124,   10,   92,   10,   32,   92,
	  124,    9,   13,   10,   32,   34,   35,   37,   42,   64,   68,   69,
	   70,   75,   83,   84,  124,    9,   13,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   15,    1,    1,   14,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    1,    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    5,    2,    2,    2,
	    2,    2,    2,   14,    1,    1,    1,    1,    1,    1,   10,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    3,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    3,    3,    2,    2,
	    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,   10,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,
	    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    3,
	    2,    2,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,
	    1,   11,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    3,    3,    2,    2,    2,    2,    2,    1,    1,    1,    1,
	    1,    1,    1,    1,   10,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    4,    3,    2,    4,   14,
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
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
	    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    1,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
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
	    0,    0,   17,   19,   21,   37,   39,   41,   44,   47,   52,   57,
	   62,   67,   71,   75,   78,   80,   82,   84,   86,   88,   90,   92,
	   94,   96,   98,  100,  102,  104,  106,  108,  110,  113,  116,  121,
	  128,  133,  135,  138,  140,  142,  144,  146,  148,  150,  152,  154,
	  156,  158,  160,  162,  164,  166,  168,  170,  172,  179,  182,  185,
	  188,  191,  194,  197,  213,  215,  217,  219,  221,  223,  225,  237,
	  240,  243,  246,  249,  252,  255,  258,  261,  264,  267,  270,  273,
	  276,  279,  282,  285,  289,  292,  295,  298,  301,  304,  307,  311,
	  314,  317,  320,  323,  326,  329,  332,  335,  338,  341,  344,  347,
	  350,  353,  356,  359,  362,  365,  368,  371,  374,  378,  382,  385,
	  388,  391,  394,  397,  399,  401,  403,  405,  407,  409,  411,  413,
	  415,  417,  419,  421,  423,  425,  427,  429,  431,  433,  435,  437,
	  439,  441,  443,  455,  458,  461,  464,  467,  470,  473,  476,  479,
	  482,  485,  488,  491,  494,  497,  500,  503,  507,  510,  513,  516,
	  519,  522,  525,  528,  531,  535,  538,  541,  544,  547,  550,  553,
	  557,  560,  563,  566,  569,  572,  574,  576,  578,  580,  582,  584,
	  586,  588,  601,  604,  607,  610,  613,  616,  619,  622,  625,  628,
	  631,  634,  637,  640,  643,  646,  649,  653,  656,  659,  662,  665,
	  668,  671,  674,  677,  680,  683,  687,  690,  693,  696,  699,  702,
	  705,  708,  711,  714,  717,  720,  723,  726,  729,  732,  735,  738,
	  741,  744,  748,  752,  755,  758,  761,  764,  767,  769,  771,  773,
	  775,  777,  779,  781,  783,  795,  798,  801,  804,  807,  810,  813,
	  816,  819,  822,  825,  828,  831,  834,  837,  840,  843,  846,  849,
	  852,  855,  858,  861,  864,  867,  870,  873,  876,  879,  882,  885,
	  888,  891,  894,  897,  900,  903,  906,  909,  912,  915,  918,  921,
	  924,  927,  930,  933,  936,  939,  942,  946,  952,  956,  959,  965,
	  981
	};
}

private static final short _lexer_index_offsets[] = init__lexer_index_offsets_0();


private static short[] init__lexer_indicies_0()
{
	return new short [] {
	    1,    3,    2,    4,    5,    6,    7,    8,    9,   10,   11,   12,
	   13,   14,   15,    2,    0,   16,    0,    2,    0,    3,    2,    4,
	    5,    6,    7,    8,    9,   10,   11,   12,   13,   14,   15,    2,
	    0,   17,    0,   18,    0,   20,   21,   19,   23,   24,   22,   27,
	   26,   28,   26,   25,   31,   30,   32,   30,   29,   31,   30,   33,
	   30,   29,   31,   30,   34,   30,   29,   36,   35,   35,    0,    3,
	   37,   37,    0,   39,   40,   38,    3,    0,   41,    0,   42,    0,
	   43,    0,   44,    0,   45,    0,   46,    0,   47,    0,   48,    0,
	   49,    0,   50,    0,   51,    0,   52,    0,   53,    0,   54,    0,
	   55,    0,   57,   58,   56,   60,   61,   59,    0,    0,    0,    0,
	   62,   63,   64,   63,   63,   66,   65,   62,    3,   67,    8,   67,
	    0,   68,    0,   55,   69,    0,   70,    0,   71,    0,   72,    0,
	   73,    0,   74,    0,   75,    0,   76,    0,   77,    0,   78,    0,
	   79,    0,   80,    0,   81,    0,   82,    0,   83,    0,   84,    0,
	   86,   85,   88,   87,   88,   89,   90,   91,   90,   89,   87,   88,
	   92,   87,   88,   93,   87,   88,   94,   87,   88,   95,   87,   88,
	   96,   87,   88,   97,   87,   99,   98,  100,  101,  102,  103,  104,
	  105,  106,  107,  108,  109,  110,  111,   98,    0,  112,    0,  113,
	    0,  114,    0,  115,    0,  117,  116,  119,  118,  119,  120,  121,
	  122,  123,  121,  124,  125,  126,  127,  120,  118,  119,  128,  118,
	  119,  129,  118,  119,  130,  118,  119,  131,  118,  119,  132,  118,
	  119,  133,  118,  119,  134,  118,  119,  135,  118,  119,  136,  118,
	  119,  137,  118,  119,  138,  118,  119,  139,  118,  119,  140,  118,
	  119,  141,  118,  119,  142,  118,  119,  143,  118,  119,  142,  144,
	  118,  119,  145,  118,  119,  146,  118,  119,  147,  118,  148,  119,
	  118,  149,  119,  118,  119,  150,  118,  119,  151,  152,  118,  119,
	  150,  118,  119,  153,  118,  119,  154,  118,  119,  155,  118,  119,
	  156,  118,  119,  157,  118,  119,  158,  118,  119,  159,  118,  119,
	  160,  118,  119,  161,  118,  119,  162,  118,  119,  163,  118,  119,
	  164,  118,  119,  165,  118,  119,  166,  118,  119,  167,  118,  119,
	  168,  118,  119,  169,  118,  119,  170,  118,  119,  171,  118,  119,
	  142,  118,  119,  166,  172,  118,  119,  142,  150,  118,  119,  173,
	  118,  119,  174,  118,  119,  175,  118,  119,  170,  118,  176,  177,
	    0,   75,    0,  178,    0,  179,    0,  180,    0,  181,    0,  182,
	    0,  183,    0,  184,    0,  185,    0,  186,    0,  187,    0,  188,
	    0,  189,    0,  190,    0,  191,    0,  192,    0,  193,    0,  194,
	    0,  195,    0,  196,    0,  197,    0,  199,  198,  201,  200,  201,
	  202,  203,  204,  205,  203,  206,  207,  208,  209,  202,  200,  201,
	  210,  200,  201,  211,  200,  201,  212,  200,  201,  213,  200,  201,
	  214,  200,  201,  215,  200,  201,  216,  200,  201,  217,  200,  201,
	  218,  200,  201,  219,  200,  201,  220,  200,  201,  221,  200,  201,
	  222,  200,  201,  223,  200,  201,  224,  200,  201,  225,  200,  201,
	  224,  226,  200,  201,  227,  200,  201,  228,  200,  201,  229,  200,
	  230,  201,  200,  231,  201,  200,  201,  232,  200,  201,  233,  200,
	  201,  232,  200,  201,  234,  235,  200,  201,  236,  200,  201,  237,
	  200,  201,  238,  200,  201,  239,  200,  201,  240,  200,  201,  224,
	  200,  201,  224,  232,  200,  201,  241,  200,  201,  242,  200,  201,
	  243,  200,  201,  239,  200,  244,  245,    0,  246,    0,  247,    0,
	  248,    0,  249,    0,  250,    0,  251,    0,  253,  252,  255,  254,
	  255,  256,  257,  258,  259,  257,  260,  261,  262,  263,  264,  256,
	  254,  255,  265,  254,  255,  266,  254,  255,  267,  254,  255,  268,
	  254,  255,  269,  254,  255,  270,  254,  255,  271,  254,  255,  272,
	  254,  255,  273,  254,  255,  274,  254,  255,  275,  254,  255,  276,
	  254,  255,  277,  254,  255,  278,  254,  255,  279,  254,  255,  280,
	  254,  255,  279,  281,  254,  255,  282,  254,  255,  283,  254,  255,
	  284,  254,  285,  255,  254,  286,  255,  254,  255,  287,  254,  255,
	  288,  254,  255,  289,  254,  255,  290,  254,  255,  279,  254,  255,
	  291,  292,  254,  255,  287,  254,  255,  293,  254,  255,  294,  254,
	  255,  295,  254,  255,  296,  254,  255,  297,  254,  255,  298,  254,
	  255,  299,  254,  255,  300,  254,  255,  301,  254,  255,  302,  254,
	  255,  303,  254,  255,  304,  254,  255,  305,  254,  255,  306,  254,
	  255,  307,  254,  255,  308,  254,  255,  309,  254,  255,  289,  254,
	  255,  306,  310,  254,  255,  279,  287,  254,  255,  311,  254,  255,
	  312,  254,  255,  313,  254,  255,  289,  254,   55,   75,    0,  314,
	    0,  315,    0,  316,    0,  317,    0,  318,    0,  319,    0,  321,
	  320,  323,  322,  323,  324,  325,  326,  325,  327,  328,  329,  330,
	  331,  324,  322,  323,  332,  322,  323,  333,  322,  323,  334,  322,
	  323,  335,  322,  323,  336,  322,  323,  337,  322,  323,  338,  322,
	  323,  339,  322,  323,  340,  322,  323,  341,  322,  323,  342,  322,
	  323,  343,  322,  323,  344,  322,  323,  345,  322,  323,  346,  322,
	  323,  347,  322,  323,  348,  322,  323,  349,  322,  323,  350,  322,
	  323,  351,  322,  323,  352,  322,  323,  353,  322,  323,  354,  322,
	  323,  355,  322,  323,  356,  322,  323,  353,  322,  323,  357,  322,
	  323,  358,  322,  323,  359,  322,  323,  360,  322,  323,  361,  322,
	  323,  362,  322,  323,  363,  322,  323,  364,  322,  323,  365,  322,
	  323,  366,  322,  323,  367,  322,  323,  368,  322,  323,  369,  322,
	  323,  370,  322,  323,  371,  322,  323,  372,  322,  323,  373,  322,
	  323,  374,  322,  323,  356,  322,  323,  375,  322,  323,  376,  322,
	  323,  377,  322,  323,  356,  322,  378,  379,  378,    0,  382,  381,
	  383,  384,  381,  380,    0,  386,  387,  385,    0,  386,  385,  382,
	  388,  386,  387,  388,  385,  382,  389,  390,  391,  392,  393,  394,
	  395,  396,  397,  398,  399,  400,  401,  389,    0,  402,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   45,   64,
	  122,  184,  248,  306,    3,    6,    7,    8,    9,    8,    8,    9,
	    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,   14,
	    4,   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,   24,
	   25,   26,   27,   28,   29,   30,  312,   32,   33,    4,   16,   33,
	    4,   16,   35,   36,    4,   35,   34,   36,   38,   39,   40,   41,
	   42,   43,   44,   31,   46,   47,   48,   49,   50,   51,   52,   53,
	   54,   55,   56,   55,   56,   56,    4,   57,   58,   59,   60,   61,
	   62,   63,    4,    4,    5,   15,   17,   31,   34,   37,   45,   64,
	  122,  184,  248,  306,   65,   66,   67,   68,   69,   70,   69,   70,
	   70,    4,   71,   85,   86,   94,  116,  118,   72,   73,   74,   75,
	   76,   77,   78,   79,   80,   81,   82,   83,   84,    4,   63,   87,
	   88,   89,   90,   91,   92,   93,   85,   95,   96,   97,   98,   99,
	  100,  101,  102,  103,  104,  105,  106,  107,  108,  109,  110,  111,
	  112,  113,  114,  115,  117,  119,  120,  121,  123,  124,  125,  126,
	  127,  128,  129,  130,  131,  132,  133,  134,  135,  136,  137,  138,
	  139,  140,  141,  142,  143,  144,  145,  146,  145,  146,  146,    4,
	  147,  161,  162,  170,  172,  180,  148,  149,  150,  151,  152,  153,
	  154,  155,  156,  157,  158,  159,  160,    4,   63,  163,  164,  165,
	  166,  167,  168,  169,  161,  171,  173,  179,  174,  175,  176,  177,
	  178,  181,  182,  183,  185,  247,  186,  187,  188,  189,  190,  191,
	  192,  193,  192,  193,  193,    4,  194,  208,  209,  217,  221,  241,
	  243,  195,  196,  197,  198,  199,  200,  201,  202,  203,  204,  205,
	  206,  207,    4,   63,  210,  211,  212,  213,  214,  215,  216,  208,
	  218,  219,  220,  222,  223,  224,  225,  226,  227,  228,  229,  230,
	  231,  232,  233,  234,  235,  236,  237,  238,  239,  240,  242,  244,
	  245,  246,  249,  250,  251,  252,  253,  254,  255,  256,  255,  256,
	  256,    4,  257,  271,  280,  283,  297,  302,  258,  259,  260,  261,
	  262,  263,  264,  265,  266,  267,  268,  269,  270,    4,  272,  273,
	  274,  275,  276,  277,  278,  279,   63,  281,  282,  284,  285,  286,
	  287,  288,  289,  290,  291,  292,  293,  294,  295,  296,  297,  298,
	  299,  300,  301,  303,  304,  305,  306,  307,  308,  310,  311,  309,
	  307,  308,  309,  307,  310,  311,    5,   15,   17,   31,   34,   37,
	   45,   64,  122,  184,  248,  306,    0
	};
}

private static final short _lexer_trans_targs[] = init__lexer_trans_targs_0();


private static short[] init__lexer_trans_actions_0()
{
	return new short [] {
	   43,    0,    0,   54,    3,    1,    0,   29,    1,   29,   29,   29,
	   29,   29,   29,   35,    0,    0,    0,    7,  139,   48,    0,  102,
	    9,    5,   45,  134,   45,    0,   33,  122,   33,   33,    0,   11,
	  106,    0,    0,  114,   25,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   57,  149,  126,    0,
	  110,   23,    0,   27,  118,   27,   51,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,   57,  144,    0,   54,    0,   81,   84,    0,    0,    0,    0,
	    0,   21,   31,  130,   60,   57,   31,   63,   57,   63,   63,   63,
	   63,   63,   63,   66,    0,    0,    0,    0,   57,  144,    0,   54,
	    0,   72,   33,   84,   84,   84,   84,   84,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   15,   15,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   78,
	   33,   84,   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,   19,   19,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	   57,  144,    0,   54,    0,   75,   33,   84,   84,   84,   84,   84,
	   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   17,   17,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,
	    0,   69,   33,   84,   84,   84,   84,   84,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   13,    0,    0,
	    0,    0,    0,    0,    0,    0,   13,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   37,   37,   54,   37,
	   87,    0,    0,   39,    0,    0,   93,   90,   41,   96,   90,   96,
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
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 312;

static final int lexer_en_main = 1;


// line 159 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"

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

    
// line 506 "java/src/main/java/gherkin/lexer/Eo.java"
	{
	cs = lexer_start;
	}

// line 185 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
    
// line 513 "java/src/main/java/gherkin/lexer/Eo.java"
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
// line 16 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replaceAll("\\\\\"\\\\\"\\\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
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
// line 136 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 774 "java/src/main/java/gherkin/lexer/Eo.java"
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
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 806 "java/src/main/java/gherkin/lexer/Eo.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/eo.java.rl"
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
