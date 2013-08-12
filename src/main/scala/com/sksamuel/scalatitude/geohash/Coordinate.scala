package com.sksamuel.scalatitude.geohash

/** @author Stephen Samuel */
trait Coordinate

class LatLonCoordinate extends Coordinate
class OSRefCoordinate extends Coordinate

trait CoordinateSystem
class UtmCoordinateSystem extends CoordinateSystem
class OSRefCoordinateSystem extends CoordinateSystem

trait Ellipsoid
class WGS84Ellipsoid extends Ellipsoid

case class Longitude(value: Double) {
  implicit def string2Longitude(str: String) = new Longitude(str.toDouble)
}
case class Latitude(value: Double) {
  implicit def string2Latitude(str: String) = new Latitude(str.toDouble)
}