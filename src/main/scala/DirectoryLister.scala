
:reset

/**/object DirectoryLister {

import java.io.File

import scala.language.implicitConversions

/* -------------------------------------------------------------------------- */

val root = new File("C:/hirose_work/work/Test/Scala_work/DirectoryLister")

val newFiles(dir: File) = {
    require(dir.isDirectory)
    dir.listFiles.sortWith((f1,f2) => f2.isDirectory)
}

var files = newFiles(root)

/* -------------------------------------------------------------------------- */

trait Node
case class FileNode(f: File) extends Node
case class DirNode(f: File) extends Node = {

}

implicit def file2Node(f: File): Node = 
    if(f.isDirectory) DirNode(f) else FileNode(f)

def nextNode(n: Node) = n

/* -------------------------------------------------------------------------- */

def nextFile: Option[File] = {
    files match {
        case Nil => None
        case fh :: fs => {
            val n = nextNode(fh)
        }
    }
}


/**/}

import DirectoryLister._

