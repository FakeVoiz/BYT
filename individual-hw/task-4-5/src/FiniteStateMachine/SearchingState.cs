using System;

namespace task_4_5.FiniteStateMachine
{
    public class SearchingState : State
    {
        public SearchingState()
        {
            Console.WriteLine("AI is searching (SearchingState)");
        }

        public override State HandleOnEnemySpotted()
        {
            return new AttackingState();
        }

        public override State HandleOnDamageTaken()
        {
            return new AttackingState();
        }

        public override State HandleOnEnemyLost()
        {
            return new IdleState();
        }
    }
}