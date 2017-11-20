import java.io.File

import scala.language.implicitConversions

case class DirectoryLister(root: File) {
    require(root.isDirectory)

    var files: List[File] = newFiles(root)

    /* ---------------------------------------------------------------------- */

    def newFiles(dir: File): List[File] = {
        require(dir.isDirectory)
        dir.listFiles.sortWith((f1,f2) => f2.isDirectory).toList
    }

    /* ---------------------------------------------------------------------- */

    def nextFile: Option[File] = {
        println(files.toString)
        files match {
            case Nil => None
            case fh :: fs => {
                if(fh.isDirectory) {
                    files = newFiles(fh) ++ fs
                }
                val ret = Some(files.head)
                files = files.tail
                ret
            }
        }
    }
}
