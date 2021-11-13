using System;

namespace task_4_5.FiniteStateMachine
{
    public class IdleState : State
    {
        public IdleState()
        {
            Console.WriteLine("AI is patrolling (IdleState)");
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
            return this;
        }
    }
}