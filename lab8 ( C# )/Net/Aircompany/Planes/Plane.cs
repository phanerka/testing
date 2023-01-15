using System.Collections.Generic;

namespace Aircompany.Planes
{
    public abstract class Plane
    {
        public string _model;
        public int _maxSpeed;
        public int _maxFlightDistance;
        public int _maxLoadCapacity;

        public Plane() { }

        public Plane(string model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity)
        {
            _model = model;
            _maxSpeed = maxSpeed;
            _maxFlightDistance = maxFlightDistance;
            _maxLoadCapacity = maxLoadCapacity;
        }

        public string Model => _model;

        public int MaxSpeed=> _maxSpeed;

        public int MaxFlightDistance => _maxFlightDistance;

        public int MaxLoadCapacity => _maxLoadCapacity;

        public override int GetHashCode()
        {
            var hashCode = -1043886837;
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(_model);
            hashCode = hashCode * -1521134295 + _maxSpeed.GetHashCode();
            hashCode = hashCode * -1521134295 + _maxFlightDistance.GetHashCode();
            hashCode = hashCode * -1521134295 + _maxLoadCapacity.GetHashCode();
            return hashCode;
        }

       
        public override bool Equals(object obj)
        {
            var plane = obj as Plane;
            return plane != null &&
                   _model == plane._model &&
                   _maxSpeed == plane._maxSpeed &&
                   _maxFlightDistance == plane._maxFlightDistance &&
                   _maxLoadCapacity == plane._maxLoadCapacity;
        }

        public override string ToString() => $"Plane: model = \'{_model}\', MaxSpeed = {_maxSpeed}" +
                $", MaxFlightDistance = {_maxFlightDistance}, MaxLoadCapacity = {_maxLoadCapacity}";
    }
}
