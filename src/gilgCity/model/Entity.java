package gilgCity.model;

public abstract class Entity {

        protected Long cost;
        protected int unitId;

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId =unitId;
        }

        private int level;

        public Long getCost() {
            return cost;
        }

        public void setCost(Long cost) {
            this.cost = cost;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public abstract void update();

        public abstract void remove(int unitId);

        public abstract double calculateScore();

    }

