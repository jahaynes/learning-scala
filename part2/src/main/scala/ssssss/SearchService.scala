package ssssss

import cats.MonadError
import cats.implicits._
import ssssss.util.StringUtils.splitOn

import scala.language.higherKinds

class SearchService[F[_]](private val retrieveImpl: Term => F[List[Url]],
                          private val storeImpl: (Term, Url) => F[Unit],
                          private val fetchImpl: Url => F[Contents])(implicit me: MonadError[F, Throwable]) {

  def retrieve(term: Term): F[List[Url]] =
    retrieveImpl(term)

  def fetchAndIndex(url: Url): F[Unit] =
    for {
      contents <- fetchImpl(url)
      terms = index(contents)
      _ <- terms traverse (storeImpl(_, url))
    } yield ()

  private def index(contents: Contents): List[Term] =
    splitOn(contents.value, _.isLetterOrDigit) map (_.toLowerCase) map Term
}
