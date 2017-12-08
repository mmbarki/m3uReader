package com.mbarki.m3uReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mbarki.m3uReader.model.ChannelM3u;
import com.mbarki.m3uReader.service.M3uParser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = M3uReaderApplication.class)
@ActiveProfiles("test")
public class M3uParserTests {

	@Autowired
	private M3uParser m3uParser;

	@Test
	public void parseChannelDescription_successTest() {
		String channelLine = "#EXTINF:-1 tvg-ID=\"TF1.fr\" tvg-name=\"FR: TF1 HD\" tvg-logo=\"http://i.imgur.com/vwYqdHv.png\" group-title=\"Canalsat\",FR: TF1 HD";

		ChannelM3u channelM3u = m3uParser.parseChannelDescription(channelLine);

		assertNotNull(channelM3u);
		assertEquals("TF1.fr", channelM3u.getTvgId());
		assertEquals("FR: TF1 HD", channelM3u.getTvgName());
		assertEquals("http://i.imgur.com/vwYqdHv.png", channelM3u.getTvLogo());
		assertEquals("Canalsat", channelM3u.getGroupTitle());

		assertEquals("FR: TF1 HD", channelM3u.getOutputName());

	}

	@Test
	public void parseChannelDescription_success_EmptyDescriptionTest() {
		String channelLine = "#EXTINF:-1,FR: TF1 HD";

		ChannelM3u channelM3u = m3uParser.parseChannelDescription(channelLine);

		assertNotNull(channelM3u);
		assertEquals("not_found", channelM3u.getTvgId());
		assertEquals("not_found", channelM3u.getTvgName());
		assertEquals("not_found", channelM3u.getTvLogo());
		assertEquals("not_found", channelM3u.getGroupTitle());

		assertEquals("FR: TF1 HD", channelM3u.getOutputName());
	}

	@Test
	public void parseChannelDescription_failTest() {
		String channelLine = "WRONG_PREFIX tvg-ID=\"TF1.fr\" tvg-name=\"FR: TF1 HD\" tvg-logo=\"http://i.imgur.com/vwYqdHv.png\" group-title=\"Canalsat\",FR: TF1 HD";

		ChannelM3u channelM3u = m3uParser.parseChannelDescription(channelLine);
		assertNull(channelM3u);
	}

}
