/*
 * Copyright 2007 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing;

import com.google.zxing.common.BitXY;
import com.google.zxing.common.CoordinateXY;
import com.google.zxing.common.MatrixRec;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Encapsulates the result of decoding a barcode within an image.</p>
 *
 * @author Sean Owen
 */
public final class Result {

  private final String text;
  private final byte[] rawBytes;
  private ResultPoint[] resultPoints;
  private final BarcodeFormat format;
  private Map<ResultMetadataType,Object> resultMetadata;
  private final long timestamp;
  private BitXY topLeft;
  private BitXY bottomRight;
  long decodeTime;
  long detectTime;
  MatrixRec matrixRec;
  int width;
  int height;

  private List<CoordinateXY> originalSqrCoordinates;

  public List<CoordinateXY> getOriginalSqrCoordinates() {
    return originalSqrCoordinates;
  }

  public void setOriginalSqrCoordinates(List<CoordinateXY> originalSqrCoordinates) {
    this.originalSqrCoordinates = originalSqrCoordinates;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public MatrixRec getMatrixRec() {
    return matrixRec;
  }

  public void setMatrixRec(MatrixRec matrixRec) {
    this.matrixRec = matrixRec;
  }

  public long getDecodeTime() {
    return decodeTime;
  }

  public void setDecodeTime(long decodeTime) {
    this.decodeTime = decodeTime;
  }

  public long getDetectTime() {
    return detectTime;
  }

  public void setDetectTime(long detectTime) {
    this.detectTime = detectTime;
  }

  public BitXY getTopLeft() {
    return topLeft;
  }

  public void setTopLeft(BitXY topLeft) {
    this.topLeft = topLeft;
  }

  public BitXY getBottomRight() {
    return bottomRight;
  }

  public void setBottomRight(BitXY bottomRight) {
    this.bottomRight = bottomRight;
  }

  public Result(String text,
                byte[] rawBytes,
                ResultPoint[] resultPoints,
                BarcodeFormat format) {
    this(text, rawBytes, resultPoints, format, System.currentTimeMillis());
  }

  public Result(String text,
                byte[] rawBytes,
                ResultPoint[] resultPoints,
                BarcodeFormat format,
                long timestamp) {
    this.text = text;
    this.rawBytes = rawBytes;
    this.resultPoints = resultPoints;
    this.format = format;
    this.resultMetadata = null;
    this.timestamp = timestamp;
  }

  /**
   * @return raw text encoded by the barcode
   */
  public String getText() {
    return text;
  }

  /**
   * @return raw bytes encoded by the barcode, if applicable, otherwise {@code null}
   */
  public byte[] getRawBytes() {
    return rawBytes;
  }

  /**
   * @return points related to the barcode in the image. These are typically points
   *         identifying finder patterns or the corners of the barcode. The exact meaning is
   *         specific to the type of barcode that was decoded.
   */
  public ResultPoint[] getResultPoints() {
    return resultPoints;
  }

  /**
   * @return {@link BarcodeFormat} representing the format of the barcode that was decoded
   */
  public BarcodeFormat getBarcodeFormat() {
    return format;
  }

  /**
   * @return {@link Map} mapping {@link ResultMetadataType} keys to values. May be
   *   {@code null}. This contains optional metadata about what was detected about the barcode,
   *   like orientation.
   */
  public Map<ResultMetadataType,Object> getResultMetadata() {
    return resultMetadata;
  }

  public void putMetadata(ResultMetadataType type, Object value) {
    if (resultMetadata == null) {
      resultMetadata = new EnumMap<>(ResultMetadataType.class);
    }
    resultMetadata.put(type, value);
  }

  public void putAllMetadata(Map<ResultMetadataType,Object> metadata) {
    if (metadata != null) {
      if (resultMetadata == null) {
        resultMetadata = metadata;
      } else {
        resultMetadata.putAll(metadata);
      }
    }
  }

  public void addResultPoints(ResultPoint[] newPoints) {
    ResultPoint[] oldPoints = resultPoints;
    if (oldPoints == null) {
      resultPoints = newPoints;
    } else if (newPoints != null && newPoints.length > 0) {
      ResultPoint[] allPoints = new ResultPoint[oldPoints.length + newPoints.length];
      System.arraycopy(oldPoints, 0, allPoints, 0, oldPoints.length);
      System.arraycopy(newPoints, 0, allPoints, oldPoints.length, newPoints.length);
      resultPoints = allPoints;
    }
  }

  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return text;
  }

}
