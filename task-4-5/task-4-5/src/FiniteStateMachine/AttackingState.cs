using System;

namespace task_4_5.FiniteStateMachine
{
    public class AttackingState : State
    {
        public AttackingState()
        {
            Console.WriteLine("AI is attacking (AttackingState)");
        }

        public override State HandleOnEnemySpotted()
        {
            return this;
        }

        public override State HandleOnDamageTaken()
        {
            return this;
        }

        public override State HandleOnEnemyLost()
        {
            return new SearchingState();
        }
    }
}