package co.s4n.practice.repository

//import cats.data.EitherT
//import scala.util.{Success, Failure}
//import monix.eval.Task
import co.s4n.practice.domain._
import org.mongodb.scala._
//import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
//import org.mongodb.scala.model.Projections._
import org.mongodb.scala.result.{InsertOneResult, UpdateResult}
//import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
//import org.mongodb.scala.model._
//import scala.concurrent._
//import ExecutionContext.Implicits.global
//import org.mongodb.scala.bson.codecs._
//import com.mongodb.reactivestreams.client.MongoClient
//import com.mongodb.reactivestreams.client.MongoCollection

class UserRepository() {
  @SuppressWarnings(Array("org.wartremover.warts.All"))
  /*var xdb: List[User] = {
    List(
      User(1, "Nicolas1", "mail1"),
      User(2, "Nicolas2", "mail2")
    )
  }*/
  val uri =
    "mongodb+srv://root:2021@cluster0.4h8bp.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"
  //System.setProperty("org.mongodb.async.type", "netty")
  val client: MongoClient = MongoClient(uri)
  val db: MongoDatabase = client.getDatabase("User")
  val collection: MongoCollection[Document] = db.getCollection("User")

  def findUser(userId: Int): SingleObservable[Document] = {
    collection.find(equal("id", userId)).first()
  }

  def createUser(user: User): SingleObservable[InsertOneResult] = {
    val doc = Document("id" -> user.id,
                       "name" -> user.name,
                       "about" -> user.about,
                       "cel" -> user.cel)
    collection.insertOne(doc)
  }

  def updateUser1(user: User): SingleObservable[UpdateResult] = {
    /*val doc = Document("id" -> user.id,
                       "name" -> user.name,
                       "about" -> user.about,
                       "cel" -> user.cel)*/
    val res = collection.updateOne(equal("id", user.id), set("name", user.name))
    res
  }
  def updateUser2(user: User): SingleObservable[UpdateResult] = {
    /*val doc = Document("id" -> user.id,
                       "name" -> user.name,
                       "about" -> user.about,
                       "cel" -> user.cel)*/
    val res =
      collection.updateOne(equal("id", user.id), set("about", user.about))
    res
  }
  def updateUser3(user: User): SingleObservable[UpdateResult] = {
    /*val doc = Document("id" -> user.id,
                       "name" -> user.name,
                       "about" -> user.about,
                       "cel" -> user.cel)*/
    val res = collection.updateOne(equal("id", user.id), set("cel", user.cel))
    res
  }
}
