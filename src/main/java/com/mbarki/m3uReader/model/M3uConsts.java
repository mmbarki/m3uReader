package com.mbarki.m3uReader.model;

public class M3uConsts {

	//	#EXTINF:-1 tvg-ID="TF1.fr" tvg-name="FR: TF1 HD" tvg-logo="http://i.imgur.com/vwYqdHv.png" group-title="Canalsat",FR: TF1 HD

	public static final String CHAR_BLANK = " ";
	public static final String CHAR_PREFIX_NAME = ",";
	public static final String CHAR_EQ = "=";
	public static final String CHAR_QUOTE = "\"";

	public static final String PLAYLIST_PREFIX = "#EXTM3U";
	public static final String PLAYLIST_URL_TVG = "url-tvg";
	public static final String PLAYLIST_REFRESH = "refresh";

	public static final String CHANNEL_PREFIX = "#EXTINF:-1";
	public static final String CHANNEL_TVG_ID = "tvg-ID";
	public static final String CHANNEL_TVG_NAME = "tvg-name";
	public static final String CHANNEL_TVG_LOGO = "tvg-logo";
	public static final String CHANNEL_GROUP_TITLE = "group-title";

}
