package generator.cells

import model._
import java.util.Random
import scala.util.Random._
import com.github.nscala_time.time.Imports._



/** CellsGenerator interface */
abstract class CellsGenerator() extends Serializable{
	/**
	  * Generate an array of cells
		* @return an array of cells
		*/
	def generate(operators: Array[Operator]) : Array[Cell]
}

/** Generate cell located at random position (x, y)
	* Where x is in [latMin, latMax] and y is in [lonMin, lonMax]
	*
	* @param nCells int, the number of cells to generate
	* @param latMin Double, the latitude of the bottom left corner of the square, default = 0.0
	* @param latMax Double, the latitude of the top right corner of the square, default = 1.0
	* @param lonMin Double, the longitude of the bottom left corner of the square, default = 0.0
	* @param lonMax Double, the longitude of the top right corner of the square, default = 1.0
	* @param dropProbability The probability that the cell have to drop a call
	*/
class BasicCellsGenerator(
	val nCells: Int,
	val latMin: Double = 0,
	val latMax: Double = 1,
	val lonMin: Double = 0,
	val lonMax: Double = 1,
	val dropProbability: Double = 0.01
) extends CellsGenerator with Serializable{
	protected val rand = new Random

	override def generate(operators: Array[Operator]) : Array[Cell] = {
		val cellsId = 1 to nCells
		cellsId.map{ id => new Cell(id, randomLocation(), operators(0), Array()) }.toArray
	}

	private def randomLocation() = new Location(
		latMin + (rand.nextDouble() * (latMax - latMin)),
		lonMin + (rand.nextDouble() * (lonMax - lonMin))
	)

}


