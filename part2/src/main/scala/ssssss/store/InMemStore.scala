package ssssss.store

import akka.util.ConcurrentMultiMap
import cats.MonadError
import com.twitter.util.Future
import ssssss.{Term, Url}

class InMemStore[K, V, F[_]](private val multiMap: ConcurrentMultiMap[K, V])
                            (implicit me: MonadError[F, Throwable]) {

  def retrieve(k: K): F[List[V]] =
    me.pure(multiMap.valueIterator(k).toList)

  def store(k: K, v: V): F[Unit] =
    me.pure(multiMap.put(k, v))
}

object InMemStore {

  def create: InMemStore[Term, Url, Future] =
    new InMemStore[Term, Url, Future](new ConcurrentMultiMap[Term, Url](100, compareUrl))(me = io.catbird.util.twitterFutureInstance)

  private def compareUrl(a: Url, b: Url): Int =
    a.value.compare(b.value)
}