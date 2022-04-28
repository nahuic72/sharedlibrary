#!/usr/bin/env groovy
package com.nahuic

class ClaseEjemplo {
   String nombre
   Integer edad

   def hacerseViejo(Integer years) {
      this.edad += years
   }
}
