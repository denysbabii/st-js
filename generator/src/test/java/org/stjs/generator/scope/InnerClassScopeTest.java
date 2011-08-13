package org.stjs.generator.scope;

import static org.stjs.generator.scope.ScopeAssert.assertScope;
import static org.stjs.generator.scope.ScopeTest.compilationUnit;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import java.io.IOException;
import org.junit.Test;
import org.stjs.generator.scope.QualifiedName.NameTypes;

public class InnerClassScopeTest {

  @Test
  public void resolvesInnerClassesInOtherFiles() throws ParseException, IOException {
    String fileName = "test/innerclasses/ClassUsingInnerClass.java";
    CompilationUnit cu =  compilationUnit(fileName);
    ScopeTest.resolveName2(cu, fileName);
    
    assertScope(cu).line(9).column(12, 0).assertName("doSth")
    .assertScopePath("root.import")
    .assertType(NameTypes.METHOD);
 
    assertScope(cu).line(10).column(12, 0).assertName("doSth")
    .assertScopePath("root.import")
    .assertType(NameTypes.METHOD);
    
    assertScope(cu).line(9).column(12, 0).assertName("doSth")
    .assertScopePath("root.import")
    .assertType(NameTypes.METHOD);
 
 
  }
}
