import React, { useState } from "react";

const CalorieTracker = () => {
  const [name, setName] = useState("");
  const [dailyGoal, setDailyGoal] = useState("");
  const [breakfast, setBreakfast] = useState("");
  const [lunch, setLunch] = useState("");
  const [dinner, setDinner] = useState("");
  const [snacks, setSnacks] = useState("");
  const [totalCalories, setTotalCalories] = useState(null);
  const [remainingCalories, setRemainingCalories] = useState(null);

  const handleCalculate = () => {
    // Validation
    if (
      !name ||
      !dailyGoal ||
      !breakfast ||
      !lunch ||
      !dinner ||
      !snacks
    ) {
      alert("Please fill in all fields.");
      return;
    }

    const values = [dailyGoal, breakfast, lunch, dinner, snacks];
    if (values.some(v => isNaN(v) || Number(v) < 0)) {
      alert("Please enter positive numbers only.");
      return;
    }

    // Calculations
    const total =
      Number(breakfast) + Number(lunch) + Number(dinner) + Number(snacks);
    const remaining = Number(dailyGoal) - total;

    setTotalCalories(total);
    setRemainingCalories(remaining);
  };

  return (
    <div style={styles.container}>
      <h2>Calorie Tracker App</h2>

      <div style={styles.form}>
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          style={styles.input}
        />
        <input
          type="number"
          placeholder="Daily Calorie Goal"
          value={dailyGoal}
          onChange={(e) => setDailyGoal(e.target.value)}
          style={styles.input}
        />
        <input
          type="number"
          placeholder="Breakfast Calories"
          value={breakfast}
          onChange={(e) => setBreakfast(e.target.value)}
          style={styles.input}
        />
        <input
          type="number"
          placeholder="Lunch Calories"
          value={lunch}
          onChange={(e) => setLunch(e.target.value)}
          style={styles.input}
        />
        <input
          type="number"
          placeholder="Dinner Calories"
          value={dinner}
          onChange={(e) => setDinner(e.target.value)}
          style={styles.input}
        />
        <input
          type="number"
          placeholder="Snacks Calories"
          value={snacks}
          onChange={(e) => setSnacks(e.target.value)}
          style={styles.input}
        />

        <button onClick={handleCalculate} style={styles.button}>
          Calculate Calories
        </button>
      </div>

      {totalCalories !== null && remainingCalories !== null && (
        <div style={styles.resultBox}>
          <h3>Results</h3>
          <p><strong>Name:</strong> {name}</p>
          <p><strong>Daily Calorie Goal:</strong> {dailyGoal}</p>
          <p><strong>Total Calories Consumed:</strong> {totalCalories}</p>
          <p
            style={{
              color: remainingCalories < 0 ? "red" : "green",
              fontWeight: "bold",
            }}
          >
            Remaining Calories: {remainingCalories} <br />
            {remainingCalories < 0
              ? "You exceeded your daily calorie goal!"
              : "You are within your daily goal!"}
          </p>
        </div>
      )}
    </div>
  );
};

const styles = {
  container: {
    maxWidth: "400px",
    margin: "50px auto",
    textAlign: "center",
    padding: "20px",
    border: "1px solid #ccc",
    borderRadius: "10px",
    boxShadow: "0 0 10px rgba(0,0,0,0.1)",
    backgroundColor: "#f9f9f9",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    gap: "10px",
  },
  input: {
    padding: "8px",
    borderRadius: "5px",
    border: "1px solid #ccc",
  },
  button: {
    backgroundColor: "#007bff",
    color: "white",
    padding: "10px",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  resultBox: {
    marginTop: "20px",
    textAlign: "left",
  },
};

export default CalorieTracker;
