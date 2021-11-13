using System;
using System.Collections.Generic;
using task_4_5.FiniteStateMachine;
using task_4_5.Mediator;
using task_4_5.ObjectPool;

namespace task_4_5
{
    class Program
    {
        private static void Main(string[] args)
        {
            ShowcaseStateMachine();
            ShowcaseMediator();
            ObjectPoolShowcase();
        }

        private static void ShowcaseStateMachine()
        {
            Console.WriteLine("============StateMachineShowcase============\n");
            
            var aiContext = new AiContext(new IdleState());
            
            aiContext.SpotEnemy();
            aiContext.LostEnemy();
            aiContext.TakeDamage();
            aiContext.LostEnemy();
            aiContext.LostEnemy();
            
            Console.WriteLine("============================================\n");
        }

        private static void ShowcaseMediator()
        {
            Console.WriteLine("============MediatorShowcase============\n");

            var aiCommunicationComponent1 = new AiCommunicationComponent();
            var aiCommunicationComponent2 = new AiCommunicationComponent();
            new AiCommunicationMediator(aiCommunicationComponent1, aiCommunicationComponent2);
            
            aiCommunicationComponent1.SpotEnemy();
            aiCommunicationComponent2.LooseEnemy();

            Console.WriteLine("============================================\n");
        }

        private static void ObjectPoolShowcase()
        {
            Console.WriteLine("============ObjectPoolShowcase============\n");
            
            const int initialNumberOfBullets = 10;
            const int additionalNumberOfBulletsToGetFromObjectPool = 5;

            var usedBullets = new List<ReusableBullet>(15);
            var objectPool = new Pool<ReusableBullet>(pool => new ReusableBullet(pool), initialNumberOfBullets);

            //Launching bullets
            for (var i = 0; i < initialNumberOfBullets + additionalNumberOfBulletsToGetFromObjectPool; i++)
            {
                var bullet = objectPool.GetReusableObject();
                bullet.Launch();
                
                usedBullets.Add(bullet);
            }

            //Reusing all of the used bullets
            for (var i = 0; i < initialNumberOfBullets + additionalNumberOfBulletsToGetFromObjectPool; i++)
            {
                usedBullets[i].Reuse();
            }
            
            //Use all of the bullets again
            for (var i = 0; i < initialNumberOfBullets + additionalNumberOfBulletsToGetFromObjectPool; i++)
            {
                var bullet = objectPool.GetReusableObject();
                bullet.Launch();
            }
            
            Console.WriteLine("============================================\n");
        }
    }
}