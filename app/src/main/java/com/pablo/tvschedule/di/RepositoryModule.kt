package com.pablo.tvschedule.di

import com.pablo.tvschedule.data.repositories.CastRepositoryImpl
import com.pablo.tvschedule.data.repositories.EpisodeRepositoryImpl
import com.pablo.tvschedule.data.repositories.ScheduleRepositoryImpl
import com.pablo.tvschedule.domain.repositories.CastRepository
import com.pablo.tvschedule.domain.repositories.EpisodeRepository
import com.pablo.tvschedule.domain.repositories.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindScheduleRepository(impl: ScheduleRepositoryImpl): ScheduleRepository

    @Binds
    abstract fun bindEpisodeRepository(impl: EpisodeRepositoryImpl): EpisodeRepository

    @Binds
    abstract fun bindCastRepository(impl: CastRepositoryImpl): CastRepository
}