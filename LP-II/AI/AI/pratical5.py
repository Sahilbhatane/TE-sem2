def evaluate_performance():
    print("🔍 Welcome to the Employee Performance Evaluation Expert System")
    print("Please answer the following questions with 'yes' or 'no'.\n")

    answers = {}

    # Collecting data (facts)
    answers['punctual'] = input("Is the employee punctual? ").strip().lower() == 'yes'
    answers['meets_goals'] = input("Does the employee meet their targets/goals? ").strip().lower() == 'yes'
    answers['teamwork'] = input("Is the employee a good team player? ").strip().lower() == 'yes'
    answers['innovation'] = input("Does the employee show innovation or take initiative? ").strip().lower() == 'yes'
    answers['attendance'] = input("Is the employee's attendance satisfactory? ").strip().lower() == 'yes'
    answers['learning'] = input("Is the employee open to learning and development? ").strip().lower() == 'yes'

    print("\n🔎 Evaluating...")

    # Rule-based reasoning
    if all(answers.values()):
        print("\n✅ Excellent performance! Recommend for promotion or bonus.")
    elif answers['punctual'] and answers['meets_goals'] and answers['teamwork']:
        print("\n👍 Good performance. Consider development opportunities.")
    elif not answers['attendance']:
        print("\n⚠️ Performance affected due to poor attendance. Address attendance issues.")
    elif not answers['meets_goals']:
        print("\n📉 Needs improvement. Failing to meet goals consistently.")
    else:
        print("\n📋 Performance is average. Recommend more supervision and training.")

if __name__ == "__main__":
    evaluate_performance()
