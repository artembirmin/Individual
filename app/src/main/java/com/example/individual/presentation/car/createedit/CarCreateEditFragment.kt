package com.example.individual.presentation.car.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentCarCreateEditBinding
import com.example.individual.model.CarFull
import com.example.individual.presentation.BaseFragment
import org.joda.time.DateTime

class CarCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentCarCreateEditBinding
    private lateinit var viewModel: CarCreateEditViewModel
    private val initParams: CarCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_car_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tpFuelingTime.setIs24HourView(true)
            toolbar.setNavigationOnClickListener { closeFragment() }
            btnSave.setOnClickListener {
                onSaveClick()
            }
            btnDelete.setOnClickListener {
                viewModel.deleteCar()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(CarCreateEditViewModel::class.java)

        val carId = initParams.id
        if (carId != null) {
            viewModel.getCar(carId)
        }

        viewModel.carLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {
            val passengersCount = etPassengersCount.text.toString().toIntOrNull() ?: run {
                showMessageByToast("Введите пассажиров")
                return
            }

            val fuelVolume = etFuelVolume.text.toString().toIntOrNull() ?: run {
                showMessageByToast("Введите объекм топлива")
                return
            }

            val car = CarFull(
                id = 0,
                gasStationId = initParams.gasStationId,
                number = etNumber.text.toString(),
                color = etColor.text.toString(),
                vehicleType = etVehicleType.text.toString(),
                ownerName = etOwnerName.text.toString(),
                fuelingTime = DateTime(
                    dpFuelingDate.year,
                    dpFuelingDate.month + 1,
                    dpFuelingDate.dayOfMonth,
                    tpFuelingTime.hour,
                    tpFuelingTime.minute
                ),
                passengersCount = passengersCount,
                fuelType = etFuelType.text.toString(),
                fuelVolume = fuelVolume
            )
            val message = when {
                car.number.isBlank() -> "Введите номер автомобиля"
                car.color.isBlank() -> "Введите цвет автомобиля"
                car.vehicleType.isBlank() -> "Введите тип автомобиля"
                car.ownerName.isBlank() -> "Введите ФИО владельца автомобиля"
                car.fuelType.isBlank() -> "Введите тип топлива"
                car.fuelingTime.isAfterNow -> "Время заправки не может быть больше текущего"
                else -> null
            }
            if (message == null) {
                viewModel.saveCar(car)
                closeFragment()
            } else {
                showMessageByToast(message)
            }
        }
    }

    private fun updateUI(car: CarFull?) {
        if (car != null) {
            with(binding) {
                etNumber.setText(car.number)
                etColor.setText(car.color)
                etVehicleType.setText(car.vehicleType)
                etNumber.setText(car.number)
                etOwnerName.setText(car.ownerName)
                dpFuelingDate.updateDate(
                    car.fuelingTime.year,
                    car.fuelingTime.monthOfYear - 1,
                    car.fuelingTime.dayOfMonth
                )
                tpFuelingTime.hour = car.fuelingTime.hourOfDay
                tpFuelingTime.minute = car.fuelingTime.minuteOfHour
                etPassengersCount.setText(car.passengersCount.toString())
                etFuelType.setText(car.fuelType)
                etFuelVolume.setText(car.fuelVolume.toString())
            }
        } else {
            with(binding) {
                val now = DateTime.now()
                dpFuelingDate.updateDate(
                    now.year,
                    now.monthOfYear - 1,
                    now.dayOfMonth
                )
                tpFuelingTime.hour = now.hourOfDay
                tpFuelingTime.minute = now.minuteOfHour
            }
        }
    }

    companion object {
        fun newInstance(initParams: CarCreateEditFragmentInitParams) =
            CarCreateEditFragment().provideInitParams(initParams) as CarCreateEditFragment
    }
}
