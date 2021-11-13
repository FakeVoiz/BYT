namespace task_4_5.Mediator
{
    public class MediatorComponent
    {
        public Mediator Mediator { protected get; set; }

        public MediatorComponent(Mediator mediator = null)
        {
            Mediator = mediator;
        }
    }
}