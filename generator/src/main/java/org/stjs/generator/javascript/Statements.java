/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.stjs.generator.javascript;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

/**
 * This is a collection of statements. Its like a block without the curly braces
 * 
 * <pre>
 * <i>Statements</i> :
 *     Statement*
 * </pre>
 */
class Statements extends AstNode {

	{
		this.type = Token.LAST_TOKEN + 1;
	}

	public Statements() {
	}

	public Statements(int pos) {
		super(pos);
	}

	public Statements(int pos, int len) {
		super(pos, len);
	}

	/**
	 * Alias for {@link #addChild}.
	 */
	public void addStatement(AstNode statement) {
		addChild(statement);
	}

	@Override
	public String toSource(int depth) {
		StringBuilder sb = new StringBuilder();
		sb.append(makeIndent(depth));
		for (Node kid : this) {
			sb.append(((AstNode) kid).toSource(depth + 1));
		}
		sb.append(makeIndent(depth));
		return sb.toString();
	}

	@Override
	public void visit(NodeVisitor v) {
		if (v.visit(this)) {
			for (Node kid : this) {
				((AstNode) kid).visit(v);
			}
		}
	}
}
