package com.joehalliwell.markdown.writer;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

public class MarkdownWriter {

	final static Logger log = LoggerFactory.getLogger(MarkdownWriter.class);
	final static Pattern newline = Pattern.compile("\r\n|\r|\n", Pattern.MULTILINE);

	final boolean breakLines = true;
	final int maxColumns = 80;
	final boolean wrapLongWords = true;
	final Writer writer;
	
	public int sectionLevel = 0;
	
	public MarkdownWriter(Writer writer) {
		this.writer = writer;
	}

	public void h1(String text, Object... args) throws IOException {
		header(1, text, args);
	}
	
	public void h2(String text, Object... args) throws IOException {
		header(2, text, args);
	}
	
	public void header(int level, String text, Object... args) throws IOException {
		if (level < sectionLevel - 1 || level > sectionLevel + 1) {
			log.warn("Invalid level {}", level);
		}
		nl();
		text = format(text, args);
		repeat("#", level);
		write(" ");
		write(text);
		write(" ");
		repeat("#", level);
		nl();
		sectionLevel = level;
	}

	public void p(String text, Object... args) throws IOException {
		nl();
		text = format(text, args);
		text = WordUtils.wrap(text, maxColumns, null, wrapLongWords);
		write(text);
		nl();
	}

	
	public void code(String code, Object... args) throws IOException {
		nl();
		write("```");
		nl();
		write(format(code, args));
		nl();
		write("```");
		nl();
	}

	public void blockquote(String text, Object... args) throws IOException {
		nl();
		text = format(text, args);
		text = WordUtils.wrap(text, maxColumns - 2, null, wrapLongWords);
		for (String line : lines(text)) {
			write("> ");
			write(line);
			nl();
		}
	}
	
	public void nl() throws IOException {
		write(System.lineSeparator());
	}
	
	private void write(String text) throws IOException {
		writer.write(text);
	}
	
	private void repeat(String str, int times) throws IOException {
		for (int i = 0; i < times; i++) {
			writer.write(str);
		}
	}

	private String format(String text, Object[] args) {
		return MessageFormatter.arrayFormat(text, args).getMessage();
	}

	private String[] lines(String text) {
		return newline.split(text);
	}
}
