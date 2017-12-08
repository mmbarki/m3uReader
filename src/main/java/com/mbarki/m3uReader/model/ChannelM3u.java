package com.mbarki.m3uReader.model;

public class ChannelM3u {

	// #EXTINF:-1 tvg-ID="TF1.fr" tvg-name="FR: TF1 HD" tvg-logo="http://i.imgur.com/vwYqdHv.png" group-title="Canalsat",FR: TF1 HD

	// tvg-ID
	private String tvgId;

	// tvg-name
	private String tvgName;

	// tvg-logo
	private String tvLogo;

	// group-title
	private String groupTitle;

	//Last String of the line 
	private String outputName;

	public String getTvgId() {
		return tvgId;
	}

	public void setTvgId(String tvgId) {
		this.tvgId = tvgId;
	}

	public String getTvgName() {
		return tvgName;
	}

	public void setTvgName(String tvgName) {
		this.tvgName = tvgName;
	}

	public String getTvLogo() {
		return tvLogo;
	}

	public void setTvLogo(String tvLogo) {
		this.tvLogo = tvLogo;
	}

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
}
