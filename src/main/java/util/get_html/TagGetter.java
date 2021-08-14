package util.get_html;

import java.util.ArrayList;
import java.util.List;

public class TagGetter {

	private Get getter;

	public TagGetter(Get get) {
		this.getter = get;
	}

	public List<String> byClass(String tag, String c) {
		return getBy("class", tag, c);
	}

	public List<String> byClass(String html, String tag, String c) {
		return getBy(html, "class", tag, c);
	}

	public List<String> byId(String tag, String id) {
		return getBy("id", tag, id);
	}

	public List<String> byId(String html, String tag, String id) {
		return getBy(html, "id", tag, id);
	}

	public String getAttribute(String html, String attribute) {
		if (html.contains(attribute)) {
			html = html.substring(html.indexOf(attribute) + attribute.length() + 2);
			return html.substring(0, html.indexOf("\""));
		} else {
			return "";
		}
	}
	
	public List<String> innerByClass(String tag, String param) {
		return getInner("class", tag, param);
	}
	
	public List<String> innerById(String tag, String param) {
		return getInner("id", tag, param);
	}
	
	public List<String> innerByClass(String html, String tag, String param) {
		return getInner(html, "class", tag, param);
	}
	
	public List<String> innerById(String html, String tag, String param) {
		return getInner(html, "id", tag, param);
	}

	public List<String> inner(String html, String tag){
		return getInner(html, tag);
	}
	
	private List<String> getInner(String html, String tag) {

		List<String> tags = new ArrayList<>();

		if (this.getter.getHtml() == null) {
			throw new IllegalArgumentException("html is missing");
		} else {
			String temp;
			while (true) {
				try {
					temp = html.substring(html.indexOf("<" + tag));
					temp = temp.substring(temp.indexOf(">") + 1);
					temp = temp.substring(0, temp.indexOf("</" + tag));
					tags.add(temp);

					html = html.substring(html.indexOf(temp) + tag.length() + 1);
				} catch (IndexOutOfBoundsException e) {
					break;
				}

			}

		}

		return tags;
	}
	
	private List<String> getInner(String html, String obj, String tag, String param) {

		List<String> tags = new ArrayList<>();

		if (this.getter.getHtml() == null) {
			throw new IllegalArgumentException("html is missing");
		} else {
			String temp;
			while (true) {
				try {
					temp = html.substring(html.indexOf("<" + tag));
					
					try {
						String[] classes = temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2).substring(0, temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2).indexOf("\"")).split(" ");

						for (int i = 0; i < classes.length; i++) {
							if (classes[i].equals(param)) {
								String _temp = getter.getHtml().substring(getter.getHtml().indexOf(temp));
								_temp = _temp.substring(0, _temp.indexOf("</" + tag) + 3 + tag.length());
								
								_temp = _temp.substring(_temp.indexOf(">") + 1);
								_temp = _temp.substring(0, _temp.indexOf("</" + tag));
								
								tags.add(_temp);
							}
						}

					} catch (IndexOutOfBoundsException e) {
						html = html.substring(html.indexOf(temp) + tag.length() + 1);
						continue;
					}
					html = html.substring(html.indexOf(temp) + tag.length() + 1);
				} catch (IndexOutOfBoundsException e) {
					break;
				}

			}

		}

		return tags;
	}
	
	private List<String> getInner(String obj, String tag, String param) {

		List<String> tags = new ArrayList<>();

		if (this.getter.getHtml() == null) {
			throw new IllegalArgumentException("html is missing");
		} else {
			String html = getter.getHtml();
			String temp;
			while (true) {
				try {
					temp = html.substring(html.indexOf("<" + tag));
					
					try {
						String[] classes = temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2).substring(0, temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2).indexOf("\"")).split(" ");

						for (int i = 0; i < classes.length; i++) {
							if (classes[i].equals(param)) {
								String _temp = getter.getHtml().substring(getter.getHtml().indexOf(temp));
								_temp = _temp.substring(0, _temp.indexOf("</" + tag) + 3 + tag.length());
								
								_temp = _temp.substring(_temp.indexOf(">") + 1);
								_temp = _temp.substring(0, _temp.indexOf("</" + tag));
								
								tags.add(_temp);
							}
						}

					} catch (IndexOutOfBoundsException e) {
						html = html.substring(html.indexOf(temp) + tag.length() + 1);
						continue;
					}
					html = html.substring(html.indexOf(temp) + tag.length() + 1);
				} catch (IndexOutOfBoundsException e) {
					break;
				}

			}

		}

		return tags;
	}

	public Get getGetter() {
		return this.getter;
	}

	private List<String> getBy(String obj, String tag, String param) {
		List<String> tags = new ArrayList<>();

		if (this.getter.getHtml() == null) {
			throw new IllegalArgumentException("html is missing");
		} else {
			String html = getter.getHtml();
			String temp;
			while (true) {
				try {
					temp = html.substring(html.indexOf("<" + tag));
					//temp = temp.substring(0, temp.indexOf(">") + 1);
					
					try {
						String[] classes = temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2)
								.substring(0,
										temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2).indexOf("\""))
								.split(" ");

						for (int i = 0; i < classes.length; i++) {
							if (classes[i].equals(param)) {
								String _temp = getter.getHtml().substring(getter.getHtml().indexOf(temp));
								
								int n = _temp.indexOf(">");

								if (_temp.charAt(n - 1) == '/') { // tags como IMG que nao tem fechamento
									tags.add(temp.substring(0, n + 1));
								}

								int openned = 1;
								int index = tag.length() + 1;

								for (int j = index; j < _temp.length(); j++) {
									if (_temp.substring(j).startsWith("<" + tag)) {
										openned += 1;
									} else if (_temp.substring(j).startsWith("</" + tag)) {
										openned -= 1;
										if (openned == 0) {
											tags.add(_temp.substring(0, j + tag.length() + 3));
											break;
										}
									}
								}
							}
						}

					} catch (IndexOutOfBoundsException e) {
						html = html.substring(html.indexOf(temp) + tag.length() + 1);
						continue;
					}
					html = html.substring(html.indexOf(temp) + tag.length() + 1);
				} catch (IndexOutOfBoundsException e) {
					break;
				}

			}

		}
		return tags;
	}

	private List<String> getBy(String html, String obj, String tag, String param) {
		List<String> tags = new ArrayList<>();

		if (this.getter.getHtml() == null) {
			throw new IllegalArgumentException("html is missing");
		} else {
			String tempHtml = html;
			String temp;
			while (true) {
				try {
					temp = tempHtml.substring(tempHtml.indexOf("<" + tag));
					//temp = temp.substring(0, temp.indexOf(">") + 1);

					try {
						String[] classes = temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2)
								.substring(0,
										temp.substring(temp.indexOf(obj + "=\"") + obj.length() + 2).indexOf("\""))
								.split(" ");

						for (int i = 0; i < classes.length; i++) {
							if (classes[i].equals(param)) {
								String _temp = getter.getHtml().substring(getter.getHtml().indexOf(temp));

								int n = _temp.indexOf(">");

								if (_temp.charAt(n - 1) == '/') { // tags como IMG que nao tem fechamento
									tags.add(temp.substring(0, n + 1));
								}

								int openned = 1;
								int index = tag.length() + 1;

								for (int j = index; j < _temp.length(); j++) {
									if (_temp.substring(j).startsWith("<" + tag)) {
										openned += 1;
									} else if (_temp.substring(j).startsWith("</" + tag)) {
										openned -= 1;
										if (openned == 0) {
											tags.add(_temp.substring(0, j + tag.length() + 3));
											break;
										}
									}
								}
							}
						}

					} catch (IndexOutOfBoundsException e) {
						tempHtml = tempHtml.substring(tempHtml.indexOf(temp) + tag.length() + 1);
						continue;
					}

					tempHtml = tempHtml.substring(tempHtml.indexOf(temp) + tag.length() + 1);

				} catch (IndexOutOfBoundsException e) {
					break;
				}

			}

		}
		return tags;
	}

}
