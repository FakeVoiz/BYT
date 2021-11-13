namespace task_4_5.FiniteStateMachine
{
    public abstract class State
    {
        public abstract State HandleOnEnemySpotted();
        public abstract State HandleOnDamageTaken();
        public abstract State HandleOnEnemyLost();
    }
}