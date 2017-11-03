"# m3uReader" 

M3U format specs

Extended tags could be used:
- format=“auto” - stream format for better player setup; can be: auto - use auto detection, mpegts - use MPEG-TS demuxer, hls - use HLS demuxer, other - use FFMpeg demuxer.
- group-title=“Favorite” - favorites (groups of channels)
- tvg-name=“Channel” - channel name in EPG file
- tvg-id=“1” - channel's id in xmltv
- url-tvg=“http://domain/file.zip” - URL to EPG file
- tvg-logo=“http://domain/channel.png” - URL to channel in PNG format
- refresh=“3600” - playlist refresh period

M3U file example:

#EXTM3U url-tvg="http://server/jtv.zip" refresh="3600"
#EXTINF:-1 group-title="Free channels",Channel One
udp://225.55.55.1:1234
#EXTINF:-1 group-title="Free channels",Channel Two
udp://225.55.55.2:1234
#EXTINF:-1 group-title="Free channels",News Channel
udp://225.55.55.3:1234
#EXTINF:-1 group-title="Free channels",News 2 Channel
http://udpxy.domain.ru:5555/udp/225.55.55.55:1234
#EXTINF:-1 group-title="Music channels",MTV
udp://225.55.55.4:1234


