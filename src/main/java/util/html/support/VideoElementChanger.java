package util.html.support;


import util.html.HtmlUtil;

public class VideoElementChanger implements HtmlUtil.ElementChanger {
	
	public static int MIN_WIDTH = 80;
	public static int MIN_HEIGHT = 60;
	public static int MAX_WIDTH = 500;
	public static int MAX_HEIGHT = 400;
	
	public static String ED_VIDEO_IMG = "ed_video_img";
	
	private boolean first = true;
	
	@Override
	public String replaceElement(String doc, HtmlParser.Element element) {
		if (hasEdVideoImgClass(doc, element)){
			// only change the first account, other will be remove
			if (!first) return "";
			
			int width = 0;
			int height = 0;
			String vid = null;
			
			String w = HtmlUtil.getAttributeValue(doc, element, "width");
			String h = HtmlUtil.getAttributeValue(doc, element, "height");
			String url = HtmlUtil.getAttributeValue(doc, element, "url");
			
			try {
				if (w != null) width = Integer.parseInt(w);
				if (h != null) height = Integer.parseInt(h);
			}catch(Exception e){
				// 
			}
			if (url != null && !url.isEmpty()){
				int index = url.indexOf("v=");
				if (index > 0){
					vid = url.substring(index + 2);
				}
			}
			if (width == 0 || height == 0){
				String style = HtmlUtil.getAttributeValue(doc, element, "style");
				if (style != null && !style.isEmpty()){
					String[] vs = style.split(";");
					for(String v : vs){
						int index = v.indexOf(":");
						if (index > 0){
							String name = v.substring(0, index).trim();
							String value = v.substring(index + 1).trim();
							if (name.equalsIgnoreCase("width")){
								if (value.endsWith("px"))value = value.substring(0, value.length() - 2);
								width = Integer.parseInt(value);
							}else if (name.equalsIgnoreCase("height")){
								if (value.endsWith("px"))value = value.substring(0, value.length() - 2);
								height = Integer.parseInt(value);
							}
						}
					}
				}
			}
			
			if (width < MIN_WIDTH) width = MIN_WIDTH;
			else if (width > MAX_WIDTH) width = MAX_WIDTH;
			if (height < MIN_HEIGHT) height = MIN_HEIGHT;
			else if (height > MAX_HEIGHT) height = MAX_HEIGHT;
			
			first = false;
			return getVideoElement(width, height, vid);
		}
		return null;
	}
	
	/**
	 * <pre>like &lt;img class="ed_video_img"/&gt;</pre>
	 * @param doc
	 * @param element
	 * @return
	 */
	public static boolean hasEdVideoImgClass(String doc, HtmlParser.Element element){
		if (element.tag != TagParser.IMG_BEGIN_TAG) return false;
		
		String attrs = doc.substring(
				element.attributeBeginIndex, 
				element.attributeEndIndex);
		AttributeParser.Attribute attr = null;
		int attrPosition = 0;
		while ((attr = AttributeParser.nextAttribute(
				attrs, attrPosition)) != null) 
		{
			attrPosition = attr.endIndex;
			String name = attrs.substring(
					attr.nameBeginIndex, 
					attr.nameEndIndex);
			String value = attrs.substring(
					attr.valueBeginIndex, 
					attr.valueEndIndex);
			if (AttributeParser.CLASS == AttributeParser.getAttributeType(name)){
				if (value != null && value.equalsIgnoreCase(ED_VIDEO_IMG))
					return true;
			}
		}
		return false;
	}

	public static String getVideoElement(int width, int height, String vid){
		StringBuilder sb = new StringBuilder();
		sb.append("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" width=\"").append(width).append("\" id=\"flashplayer\" height=\"").append(height).append("\">")
        .append("<param name=\"movie\" value=\"http://cache.tv.qq.com/qqplayerout.swf\" />")
        .append("<param name=\"allowFullScreen\" value=\"true\" />")
        .append("<param name=\"allowScriptAccess\" value=\"always\" />")
        .append("<param name=\"allownetworking\" value=\"all\" />")
        .append("<param name=\"wmode\" value=\"opaque\" />")
        .append("<param name=\"flashvars\" value=\"f=3&vid=").append(vid).append("\">")
        .append("<embed type=\"application/x-shockwave-flash\"")
        .append(" src=\"http://cache.tv.qq.com/qqplayerout.swf\"")
        .append(" width=\"").append(width).append("\" height=\"").append(height).append("\"")
        .append(" name=\"flashplayer\" quality=\"high\" allowScriptAccess=\"always\" wmode=\"opaque\"")
        .append(" allownetworking=\"all\" allowFullScreen=\"true\" flashvars=\"vid=").append(vid).append("\"></embed></object>");
		return sb.toString();
	}
}
