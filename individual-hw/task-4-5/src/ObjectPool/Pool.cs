using System;
using System.Collections.Generic;

namespace task_4_5.ObjectPool
{
    public class Pool<T>
    {
        private readonly Queue<T> _reusableObjects;
        private readonly Func<Pool<T>,T> _reusableFactoryMethod;
        
        public Pool(Func<Pool<T>,T> reusableFactoryMethod, int initialNumberOfObjects)
        {
            _reusableFactoryMethod = reusableFactoryMethod;
            _reusableObjects = new Queue<T>(initialNumberOfObjects);

            Console.WriteLine($"Creating {initialNumberOfObjects} objects of type {typeof(T)}");
            for (var i = 0; i < initialNumberOfObjects; i++)
                _reusableObjects.Enqueue(_reusableFactoryMethod(this));
        }
        
        public T GetReusableObject()
        {
            T reusableObject;

            if (_reusableObjects.Count < 1)
            {
                Console.WriteLine($"Creating new reusable object of type {typeof(T)}");
                reusableObject = _reusableFactoryMethod(this);
            }
            else
            {
                Console.WriteLine($"Getting object of type {typeof(T)} from pool");
                reusableObject =  _reusableObjects.Dequeue();
            }

            return reusableObject;
        }

        public void AddReusableObject(T reusableObject)
        {
            Console.WriteLine($"Adding object of type {typeof(T)} to the pool");
            
            if(reusableObject != null)
                _reusableObjects.Enqueue(reusableObject);
        }
    }
}