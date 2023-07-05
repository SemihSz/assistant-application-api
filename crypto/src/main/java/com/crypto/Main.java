package com.crypto;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Semih, 19.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public class Main {
    public static class Node {
        int minimum;
        int maximum;
        Node(int minimum, int maximum)
        {
            this.minimum = minimum;
            this.maximum = maximum;
        }
    }

    public static int getMid(int s, int e)
    {
        return s + (e - s) / 2;
    }

    /*  A recursive function to get the minimum and maximum
     value in a given range of array indexes. The following
     are parameters for this function.

      st    --> Pointer to segment tree
      index --> Index of current node in the segment tree.
     Initially 0 is passed as root is always at index 0 ss &
     se  --> Starting and ending indexes of the segment
                    represented  by current node, i.e.,
     st[index] qs & qe  --> Starting and ending indexes of
     query range */
    public static Node MaxMinUntill(Node[] st, int ss,
                                    int se, int qs, int qe,
                                    int index)
    {
        // If segment of this node is a part of given range,
        // then return
        //  the minimum and maximum node of the segment
        Node tmp;
        Node left;
        Node right;
        if (qs <= ss && qe >= se)
            return st[index];
        // If segment of this node is outside the given
        // range
        if (se < qs || ss > qe) {
            tmp = new Node(Integer.MAX_VALUE,
                    Integer.MIN_VALUE);
            return tmp;
        }
        // If a part of this segment overlaps with the given
        // range
        int mid = getMid(ss, se);
        left = MaxMinUntill(st, ss, mid, qs, qe,
                2 * index + 1);
        right = MaxMinUntill(st, mid + 1, se, qs, qe,
                2 * index + 2);
        tmp = new Node(
                Math.min(left.minimum, right.minimum),
                Math.max(left.maximum, right.maximum));
        return tmp;
    }

    // Return minimum and maximum of elements in range from
    // index qs (query start) to qe (query end).  It mainly
    // uses MaxMinUtill()
    public static Node MaxMin(Node[] st, int n, int qs,
                              int qe)
    {
        Node tmp;
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            tmp = new Node(Integer.MAX_VALUE,
                    Integer.MIN_VALUE);
            return tmp;
        }
        return MaxMinUntill(st, 0, n - 1, qs, qe, 0);
    }

    // A recursive function that constructs Segment Tree for
    // array[ss..se]. si is index of current node in segment
    // tree st
    public static void constructSTUtil(int[] arr, int ss,
                                       int se, Node[] st,
                                       int si)
    {
        // If there is one element in array, store it in
        // current
        // node of segment tree and return
        if (ss == se) {
            st[si] = new Node(arr[ss], arr[ss]);
            return;
        }

        // If there are more than one elements, then recur
        // for left and right subtrees and store the minimum
        // and maximum of two values in this node
        int mid = getMid(ss, se);
        constructSTUtil(arr, ss, mid, st, si * 2 + 1);
        constructSTUtil(arr, mid + 1, se, st, si * 2 + 2);
        int min = Math.min(st[si * 2 + 1].minimum,
                st[si * 2 + 2].minimum);
        int max = Math.max(st[si * 2 + 1].maximum,
                st[si * 2 + 2].maximum);
        st[si] = new Node(min, max);
    }

    /* Function to construct segment tree from given array.
     This function allocates memory for segment tree and
     calls constructSTUtil() to fill the allocated memory */
    public static Node[] constructST(int[] arr, int n)
    {
        // Allocate memory for segment tree
        // Height of segment tree
        int x = (int)(Math.ceil(Math.log(n) / Math.log(2)));
        // Maximum size of segment tree
        int max_size = 2 * (int)(Math.pow(2, x)) - 1;
        Node[] st = new Node[max_size];
        // Fill the allocated memory st
        constructSTUtil(arr, 0, n - 1, st, 0);
        return st;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int[] arr = { 3, 2, 3, 5, 9, 3 };
        int n = arr.length;
        Node[] st = constructST(arr, n);
        int qs = 0; // Starting index of query range
        int qe = 8; // Ending index of query range
        Node result = MaxMin(st, n, qs, qe);

        System.out.println("Minimum = " + result.minimum
                + " and Maximum = "
                + result.maximum);








// returns the ClassLoader object

 //           System.out.println(LocalDateTime.now());

//            Class cls = Class.forName("com.crypto.entity.AbstractBaseEntity");
//            ClassLoader cLoader = cls.getClassLoader();
//            Class cls2 = Class.forName("com.crypto.entity.AbstractBaseEntity", true, cLoader);


         /* returns the Class object associated with the class or interface
            with the given string name, using the given classloader. */
           // Class cls2 = Class.forName("java.lang.Thread", true, cLoader);

            // returns the name of the class
//            System.out.println("Class = " + cls.getName());
//            System.out.println("Class = " + cls2.getName());
//        } catch(ClassNotFoundException ex) {
//            System.out.println(ex.toString());
//        }

//        FieldSpec fieldSpec = FieldSpec.builder(LocalDateTime.class, "time")
//                .addAnnotation(Id.class)
//                .addModifiers(Modifier.PUBLIC)
//                .build();
//
//        FieldSpec fieldSpec1 = FieldSpec.builder(BigDecimal.class, "price")
//                .addModifiers(Modifier.PUBLIC)
//                .build();
//
//
//        FieldSpec fieldSpec2 = FieldSpec.builder(BigDecimal.class, "volume")
//                .addModifiers(Modifier.PUBLIC)
//                .build();
//
//        FieldSpec fieldSpec3 = FieldSpec.builder(BigDecimal.class, "marketCap")
//                .addModifiers(Modifier.PUBLIC)
//                .build();
//
//
//        TypeSpec spec = TypeSpec
//                .classBuilder("Bnb")
//                .addModifiers(Modifier.PUBLIC)
//                .addAnnotation(Entity.class)
//                .addAnnotation(Builder.class)
//                .addAnnotation(NoArgsConstructor.class)
//                .addAnnotation(AllArgsConstructor.class)
//                .addAnnotation(Getter.class)
//                .addAnnotation(Setter.class)
//                .addField(fieldSpec)
//                .addField(fieldSpec1)
//                .addField(fieldSpec2)
//                .addField(fieldSpec3)

//                .addAnnotation(AnnotationSpec.builder(Cache.class)
//                        .addMember("usage", "$T.$L", CacheConcurrencyStrategy.class,
//                                CacheConcurrencyStrategy.NONSTRICT_READ_WRITE.name())
//                        .build())
//                .build();
//
//        JavaFile javaFile = JavaFile.builder("com.crypto.entity", spec)
//                .indent("    ")
//                .build();
//
//        Path path = Paths.get("src/main/java");
//        javaFile.writeTo(path);

    }
}
