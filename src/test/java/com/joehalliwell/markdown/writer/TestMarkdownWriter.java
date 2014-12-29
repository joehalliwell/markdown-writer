package com.joehalliwell.markdown.writer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.junit.Test;

public class TestMarkdownWriter {
	
	final static String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec turpis nunc. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras eu facilisis massa. Pellentesque mollis feugiat ornare. Pellentesque congue dui sem, in posuere sapien suscipit id. Etiam laoreet quis velit nec vehicula. Pellentesque vitae consequat nisi, quis auctor eros. Pellentesque eget massa non nulla rutrum consectetur. Integer ante nulla, lacinia in iaculis a, auctor vel eros. Curabitur id leo tempus, rhoncus diam quis, tincidunt massa. Ut id sagittis tellus. Donec non tempor urna. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec id est orci. Fusce semper purus orci, et semper nulla dapibus in.";

	@Test
	public void test() throws IOException {
		StringWriter output = new StringWriter();
		MarkdownWriter writer = new MarkdownWriter(output);
		writer.h1("My first markdown document");
		writer.p(loremIpsum);
		writer.p("A landslide has injured more than {} people", 20);
		writer.h2("A subheading");
		writer.blockquote("This is some text\nIt will be blockquoted\nEven though it has nice breaks");
		writer.blockquote(loremIpsum);
		System.out.println(output.toString());
	}

}
