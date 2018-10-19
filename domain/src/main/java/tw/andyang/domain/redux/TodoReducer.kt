package tw.andyang.domain.redux

interface TodoReducer {
    fun newState(currentState : TodoState): TodoState
}