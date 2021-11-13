using System;

namespace task_4_5.FiniteStateMachine
{
    public class AiContext
    {
        private State _currentState;

        public AiContext(State _initialState)
        {
            _currentState = _initialState;
        }

        public void SpotEnemy()
        {
            Console.WriteLine("Enemy was spotted");
            _currentState = _currentState.HandleOnEnemySpotted();
        }

        public void TakeDamage()
        {
            Console.WriteLine("Damage was taken");
            _currentState = _currentState.HandleOnDamageTaken();
        }

        public void LostEnemy()
        {
            Console.WriteLine("Enemy was lost");
            _currentState = _currentState.HandleOnEnemyLost();
        }
    }
}