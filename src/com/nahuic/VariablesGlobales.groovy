#!/usr/bin/env groovy
package com.nahuic

class VariablesGlobales {
    static String mivar = "bar"

   // Para referirse a esta variable en una pipeline:
   //
   // import com.nahuic.VariablesGlobales
   // println VariablesGlobales.mivar
}
