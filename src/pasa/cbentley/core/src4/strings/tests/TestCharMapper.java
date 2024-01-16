package pasa.cbentley.core.src4.strings.tests;

import pasa.cbentley.core.src4.helpers.StringBBuilder;
import pasa.cbentley.core.src4.strings.CharMapper;
import pasa.cbentley.testing.engine.TestCaseBentley;

public class TestCharMapper extends TestCaseBentley {

   public void setupAbstract() {

   }

   public void testCharMapper1Char() {

      CharMapper cm = new CharMapper(uc);
      cm.setSource("I");

      //nothing
      cm.reset();
      cm.build();
      assertEquals("I", cm.getStringMapped());
      assertEquals("I", cm.getStringSrc());

      //char replace with char
      cm.reset();
      cm.opReplaceChar(0, 'K');
      cm.build();
      assertEquals("K", cm.getStringMapped());
      assertEquals("I", cm.getStringSrc());

      //char replace with String
      cm.reset();
      cm.opReplaceWith(0, "Str");
      cm.build();
      assertEquals("Str", cm.getStringMapped());
      assertEquals("I", cm.getStringSrc());

      //remove
      cm.reset();
      cm.opRemove(0);
      cm.build();
      assertEquals("", cm.getStringMapped());
      assertEquals("I", cm.getStringSrc());
   }

   public void testCharMapper2CharsEnd() {

      CharMapper cm = new CharMapper(uc);
      cm.setSource("IT");

      //nothing
      cm.reset();
      cm.build();
      assertEquals("IT", cm.getStringMapped());
      assertEquals("IT", cm.getStringSrc());

      //char replace with char
      cm.reset();
      cm.opReplaceChar(1, 'K');
      cm.build();
      assertEquals("IK", cm.getStringMapped());
      assertEquals("IT", cm.getStringSrc());

      //char replace with String
      cm.reset();
      cm.opReplaceWith(1, "Str");
      cm.build();
      assertEquals("IStr", cm.getStringMapped());
      assertEquals("IT", cm.getStringSrc());

      //remove
      cm.reset();
      cm.opRemove(1);
      cm.build();
      assertEquals("I", cm.getStringMapped());
      assertEquals("IT", cm.getStringSrc());

   }

   public void testCharMapper2CharsStart() {

      CharMapper cm = new CharMapper(uc);
      cm.setSource("IT");

      //nothing
      cm.reset();
      cm.build();
      assertEquals("IT", cm.getStringMapped());
      assertEquals("IT", cm.getStringSrc());

      //char replace with char
      cm.reset();
      cm.opReplaceChar(0, 'K');
      cm.build();
      assertEquals("KT", cm.getStringMapped());
      assertEquals("I", cm.getStringSrc(0,1));
      assertEquals("IT", cm.getStringSrc());

      //char replace with String
      cm.reset();
      cm.opReplaceWith(0, "Str");
      cm.build();
      assertEquals("StrT", cm.getStringMapped());
      assertEquals("I", cm.getStringSrc(0,1));
      assertEquals("I", cm.getStringSrc(0,2));
      assertEquals("IT", cm.getStringSrc());

      //remove
      cm.reset();
      cm.opRemove(0);
      cm.build();
      assertEquals("T", cm.getStringMapped());
      assertEquals("IT", cm.getStringSrc(0,1));
      assertEquals("IT", cm.getStringSrc());
   }

   public void testCharMapper3Chars2First() {

      CharMapper cm = new CharMapper(uc);
      cm.setSource("ITE");

      //nothing
      cm.reset();
      cm.build();
      assertEquals("ITE", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc());

      //char replace with char
      cm.reset();
      cm.opReplaceChar(0, 'a');
      cm.opReplaceChar(1, 'b');
      cm.build();
      assertEquals("abE", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc());

      //char replace with String
      cm.reset();
      cm.opReplaceWith(0, "abc");
      cm.opReplaceWith(1, "Str");
      cm.build();
      assertEquals("abcStrE", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc());

      //remove
      cm.reset();
      cm.opRemove(0);
      cm.opRemove(1);
      cm.build();
      assertEquals("E", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc(0,1));
      assertEquals("ITE", cm.getStringSrc());
   }

   public void testCharMapper3CharsMid() {

      CharMapper cm = new CharMapper(uc);
      cm.setSource("ITE");

      //nothing
      cm.reset();
      cm.build();
      assertEquals("ITE", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc());

      //char replace with char
      cm.reset();
      cm.opReplaceChar(1, 'K');
      cm.build();
      assertEquals("IKE", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc());

      //char replace with String
      cm.reset();
      cm.opReplaceWith(1, "Str");
      cm.build();
      assertEquals("IStrE", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc());

      //remove
      cm.reset();
      cm.opRemove(1);
      cm.build();
      assertEquals("IE", cm.getStringMapped());
      assertEquals("ITE", cm.getStringSrc(0,2));
      assertEquals("ITE", cm.getStringSrc());
   }

   public void testCharMapperWant() {

      CharMapper cm = new CharMapper(uc);

      char[] data = "##want.###".toCharArray();
      int offset = 2;
      int len = data.length - 5;

      cm.setSource(data, offset, len);

      cm.opReplaceOne(0, '!');
      cm.build();
      assertEquals("!ant.", cm.getStringMapped());
      assertEquals('w', cm.getModelChar(0));
      assertEquals('a', cm.getModelChar(1));
   }

   public void testCharMapperNewLine() {

      CharMapper cm = new CharMapper(uc);

      String str = "\n\t\n";

      assertEquals(3, str.length());
      
      assertEquals('\n', str.charAt(0));
      
      cm.setSource(str);
      cm.opReplaceChar(0,' ');
      cm.opRemove(1);
      cm.opReplaceChar(2,' ');
      cm.build();

      assertEquals("  ", cm.getStringMapped());
      assertEquals('\n', cm.getModelChar(0));
      assertEquals('\n', cm.getModelChar(1)); //1 does not know

      assertEquals("\n", cm.getStringSrc(0,1));
      assertEquals("\n\t\n", cm.getStringSrc(0,2));
      
   }
   
   public void testCharMapper() {

      CharMapper cm = new CharMapper(uc);

      char[] data = "##I want.\n Replace i with str.###".toCharArray();
      int offset = 2;
      int len = data.length - 5;

      cm.setSource(data, offset, len);

      cm.opReplaceOne(6, '!');

      cm.opRemove(7);

      cm.opReplaceWith(17, "str");

      cm.opRemove(27);

      cm.build();

      assertEquals("I want! Replace str with str", cm.getStringMapped());
      assertEquals(" want.", cm.getStringSrc(1,6));
      assertEquals(" want.\n Re", cm.getStringSrc(1,10));

      assertEquals("st", cm.getStringSrc(25,2));
      assertEquals("str.", cm.getStringSrc(25,3)); //add one and take the hidden char
      
      StringBBuilder sb = new StringBBuilder(uc);
      cm.appendStringSrc(sb, 0, 10);
      assertEquals("I want.\n Re", sb.toString());

   }

}
