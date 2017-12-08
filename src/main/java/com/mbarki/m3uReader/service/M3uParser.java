package com.mbarki.m3uReader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.mbarki.m3uReader.model.ChannelM3u;
import com.mbarki.m3uReader.model.M3uConsts;

@Component
public class M3uParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(M3uParser.class);

	public ChannelM3u parseChannelDescription(String channelLine) {
		// empty channel line
		if (Strings.isNullOrEmpty(channelLine))
			return null;

		// not a channel description line
		if (!channelLine.startsWith(M3uConsts.CHANNEL_PREFIX))
			// TODO: replace with exception managing
			return null;

		ChannelM3u channelM3u = new ChannelM3u();
		channelM3u.setTvgId(this.getInformation(channelLine, M3uConsts.CHANNEL_TVG_ID));
		channelM3u.setTvgName(this.getInformation(channelLine, M3uConsts.CHANNEL_TVG_NAME));
		channelM3u.setTvLogo(this.getInformation(channelLine, M3uConsts.CHANNEL_TVG_LOGO));
		channelM3u.setGroupTitle(this.getInformation(channelLine, M3uConsts.CHANNEL_GROUP_TITLE));

		channelM3u.setOutputName(this.getOutputName(channelLine));

		return channelM3u;
	}

	// #EXTINF:-1 tvg-ID="TF1.fr" tvg-name="FR: TF1 HD" tvg-logo="http://i.imgur.com/vwYqdHv.png" group-title="Canalsat",FR: TF1 HD
	private String getInformation(String channelLine, String information) {
		String result = "not_found";

		if (!channelLine.contains(information)) {
			LOGGER.debug("[get information from channel line] '{}' not found", information);
			return result;
		}

		int begin = channelLine.indexOf(information + M3uConsts.CHAR_EQ + M3uConsts.CHAR_QUOTE) + information.length() + M3uConsts.CHAR_EQ.length() + M3uConsts.CHAR_QUOTE.length();
		int end = channelLine.indexOf(M3uConsts.CHAR_QUOTE, begin);
		try {
			result = channelLine.substring(begin, end);
		} catch (Exception e) {
			LOGGER.error("[get information from channel line] Error when attempt parsing '{}', ", information);
		}

		return result;
	}

	private String getOutputName(String channelLine) {
		String result = "not_found";

		try {
			int begin = channelLine.lastIndexOf(M3uConsts.CHAR_PREFIX_NAME) + 1;
			int end = channelLine.length();
			result = channelLine.substring(begin, end);
		} catch (Exception e) {
			LOGGER.error("[get information from channel line] Error when attempt parsing 'channel output name'");
		}

		return result;
	}
}