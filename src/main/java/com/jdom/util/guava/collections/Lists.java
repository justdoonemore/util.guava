/** 
 *  Copyright (C) 2012  Just Do One More
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jdom.util.guava.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

/**
 * @author djohnson
 * 
 */
public final class Lists {

	private Lists() {
	}

	public static <E> ArrayList<E> newArrayList() {
		return com.google.common.collect.Lists.newArrayList();
	}

	public static <E> ArrayList<E> newArrayList(E... elements) {
		return com.google.common.collect.Lists.newArrayList(elements);
	}

	public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
		return com.google.common.collect.Lists.newArrayList(elements);
	}

	public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
		return com.google.common.collect.Lists.newArrayList(elements);
	}

	public static <E> ArrayList<E> newArrayListWithCapacity(int initialArraySize) {
		return com.google.common.collect.Lists
				.newArrayListWithCapacity(initialArraySize);
	}

	public static <E> ArrayList<E> newArrayListWithExpectedSize(
			int estimatedSize) {
		return com.google.common.collect.Lists
				.newArrayListWithExpectedSize(estimatedSize);
	}

	public static <E> LinkedList<E> newLinkedList() {
		return com.google.common.collect.Lists.newLinkedList();
	}

	public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
		return com.google.common.collect.Lists.newLinkedList(elements);
	}

	public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
		return com.google.common.collect.Lists.newCopyOnWriteArrayList();
	}

	public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(
			Iterable<? extends E> elements) {
		return com.google.common.collect.Lists
				.newCopyOnWriteArrayList(elements);
	}

	public static <E> List<E> asList(E first, E[] rest) {
		return com.google.common.collect.Lists.asList(first, rest);
	}

	public static <E> List<E> asList(E first, E second, E[] rest) {
		return com.google.common.collect.Lists.asList(first, second, rest);
	}

	public static <F, T> List<T> transform(List<F> fromList,
			Function<? super F, ? extends T> function) {
		return com.google.common.collect.Lists.transform(fromList, function);
	}

	public static <T> List<List<T>> partition(List<T> list, int size) {
		return com.google.common.collect.Lists.partition(list, size);
	}

	public static ImmutableList<Character> charactersOf(String string) {
		return com.google.common.collect.Lists.charactersOf(string);
	}

	public static List<Character> charactersOf(CharSequence sequence) {
		return com.google.common.collect.Lists.charactersOf(sequence);
	}

	public static <T> List<T> reverse(List<T> list) {
		return com.google.common.collect.Lists.reverse(list);
	}
}
