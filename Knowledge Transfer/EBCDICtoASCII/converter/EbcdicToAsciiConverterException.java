/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ebcdic.to.ascii.converter;

/**
 *
 * @author 257592
 */
public class EbcdicToAsciiConverterException extends RuntimeException {

  public EbcdicToAsciiConverterException(String message, Throwable e) {
    super(message, e);
  }

  public EbcdicToAsciiConverterException(String message) {
    super(message);
  }

}