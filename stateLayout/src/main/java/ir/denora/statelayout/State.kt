package ir.denora.statelayout

sealed class State

object ContentState : State()
object LoadingState : State()
object EmptyState : State()
object NotFoundState : State()
object NoNetworkState : State()
object ErrorState : State()
